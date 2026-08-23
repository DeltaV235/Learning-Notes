"""LangGraph agent that reviews and executes EXA searches."""

from __future__ import annotations

import os
from typing import Any, Literal, cast

from langchain.chat_models import init_chat_model
from langchain_core.messages import AIMessage, SystemMessage, ToolMessage
from langchain_core.messages.tool import ToolCall
from langchain_core.tools import ToolException, tool
from langchain_exa import ExaSearchResults
from langgraph.graph import END, START, MessagesState, StateGraph
from langgraph.runtime import Runtime
from langgraph.types import Command, interrupt
from pydantic import SecretStr
from typing_extensions import TypedDict


class Context(TypedDict, total=False):
    """Runtime-only configuration exposed by Studio assistants."""

    username: str


class AgentState(MessagesState, total=False):
    """Conversation state plus the tool call approved by the reviewer."""

    pending_tool_call: ToolCall | None
    review_error: str | None


@tool
def exa_search(query: str) -> list[dict[str, Any]]:
    """使用 EXA 搜索需要实时或外部信息的问题，并返回最多 5 条网页结果."""
    api_key = os.getenv("EXA_API_KEY")
    if not api_key:
        raise ToolException("未配置 EXA_API_KEY，无法执行 EXA 搜索。")

    # 将可消耗搜索配额的客户端创建延迟到审核成功后的独立执行节点。
    search_tool = ExaSearchResults(exa_api_key=SecretStr(api_key))
    return cast(
        list[dict[str, Any]],
        search_tool.invoke(
            {
                "query": query,
                "num_results": 5,
                "type": "auto",
                "livecrawl": "fallback",
                "text_contents_options": {"max_characters": 2_000},
                "highlights": True,
            }
        ),
    )


tools = [exa_search]


def _current_tool_call(state: AgentState) -> ToolCall:
    """Return the pending call or the latest model-generated tool call."""
    if pending := state.get("pending_tool_call"):
        return pending

    messages = state.get("messages", [])
    if not messages or not isinstance(messages[-1], AIMessage):
        raise RuntimeError("审核节点没有找到模型生成的 AIMessage。")
    if len(messages[-1].tool_calls) != 1:
        raise RuntimeError("EXA agent 每轮必须且只能生成一个工具调用。")
    return messages[-1].tool_calls[0]


def _review_payload(
    tool_call: ToolCall,
    review_error: str | None,
) -> dict[str, list[dict[str, Any]]]:
    """Build the Agent Chat UI HITL payload for one EXA call."""
    description = "EXA 搜索将访问外部网络并可能消耗 API 配额，请确认查询参数。"
    if review_error:
        description = f"上次编辑未通过校验：{review_error} 请修改后重新提交。{description}"

    return {
        "action_requests": [
            {
                "name": tool_call["name"],
                "args": tool_call["args"],
                "description": description,
            }
        ],
        "review_configs": [
            {
                "action_name": tool_call["name"],
                "allowed_decisions": ["approve", "edit", "reject"],
            }
        ],
    }


def _invalid_review(
    tool_call: ToolCall,
    message: str,
) -> Command[Literal["review_tools", "tools", "call_model"]]:
    """Commit the validation error, then create a fresh review task."""
    return Command(
        update={"pending_tool_call": tool_call, "review_error": message},
        goto="review_tools",
    )


def review_tool_call(
    state: AgentState,
) -> Command[Literal["review_tools", "tools", "call_model"]]:
    """Pause for review and route a validated decision without executing EXA."""
    tool_call = _current_tool_call(state)
    hitl_response = interrupt(
        _review_payload(tool_call, state.get("review_error"))
    )

    if not isinstance(hitl_response, dict):
        return _invalid_review(tool_call, "Agent Chat UI 返回了无效的 HITL 响应。")
    decisions = hitl_response.get("decisions")
    if not isinstance(decisions, list) or len(decisions) != 1:
        return _invalid_review(
            tool_call,
            "Agent Chat UI 必须为当前 EXA 请求返回且只返回一个决定。",
        )

    decision = decisions[0]
    if not isinstance(decision, dict):
        return _invalid_review(tool_call, "Agent Chat UI 返回了无效的 HITL 决定。")

    decision_type = decision.get("type")
    if decision_type == "approve":
        return Command(
            update={"pending_tool_call": tool_call, "review_error": None},
            goto="tools",
        )

    if decision_type == "reject":
        reason = decision.get("message")
        suffix = f"理由：{reason}" if isinstance(reason, str) and reason else ""
        tool_message = ToolMessage(
            content=(
                "用户拒绝执行 EXA 搜索。请不要自动重试，除非用户再次明确要求。"
                f"{suffix}"
            ),
            name=tool_call["name"],
            tool_call_id=tool_call["id"],
            status="error",
        )
        return Command(
            update={
                "messages": [tool_message],
                "pending_tool_call": None,
                "review_error": None,
            },
            goto="call_model",
        )

    if decision_type != "edit":
        return _invalid_review(
            tool_call,
            f"不支持的 Agent Chat UI 决定：{decision_type!r}。",
        )

    edited_action = decision.get("edited_action")
    if not isinstance(edited_action, dict):
        return _invalid_review(tool_call, "编辑决定缺少 edited_action。")
    if edited_action.get("name") != "exa_search":
        return _invalid_review(
            tool_call,
            "编辑后的 action name 必须仍然是 exa_search。",
        )
    edited_args = edited_action.get("args")
    if not isinstance(edited_args, dict):
        return _invalid_review(tool_call, "编辑决定缺少工具参数。")
    edited_query = edited_args.get("query")
    if not isinstance(edited_query, str) or not edited_query.strip():
        return _invalid_review(tool_call, "编辑后的 EXA query 不能为空。")

    edited_tool_call = ToolCall(
        type="tool_call",
        name="exa_search",
        args={"query": edited_query.strip()},
        id=tool_call["id"],
    )
    reviewed_message = cast(AIMessage, state["messages"][-1]).model_copy(deep=True)
    reviewed_message.tool_calls = [edited_tool_call]
    return Command(
        update={
            "messages": [reviewed_message],
            "pending_tool_call": edited_tool_call,
            "review_error": None,
        },
        goto="tools",
    )


def execute_reviewed_tool(state: AgentState) -> dict[str, Any]:
    """Execute the approved call in a task that contains no interrupt."""
    tool_call = state.get("pending_tool_call")
    if tool_call is None:
        raise RuntimeError("EXA 执行节点缺少已审核的工具调用。")

    tool_message = exa_search.invoke(tool_call)
    if not isinstance(tool_message, ToolMessage):
        raise RuntimeError("EXA 工具没有返回 ToolMessage。")
    return {
        "messages": [tool_message],
        "pending_tool_call": None,
        "review_error": None,
    }


async def call_model(
    state: AgentState,
    runtime: Runtime[Context],
) -> dict[str, Any]:
    """Let DeepSeek answer directly or request the EXA tool."""
    username = (runtime.context or {}).get("username", "用户")
    system_message = SystemMessage(
        content=(
            f"你是 {username} 的助手。"
            "遇到新闻、近期变化、网页资料或无法确定的外部事实时，"
            "每轮最多调用一次 exa_search，再根据搜索结果回答并保留来源 URL；"
            "不需要外部信息时直接回答。"
        )
    )
    model = init_chat_model(
        model="deepseek:deepseek-v4-flash",
        temperature=0,
    )
    response = await model.bind_tools(tools, parallel_tool_calls=False).ainvoke(
        [system_message, *state["messages"]]
    )
    return {"messages": [response]}


def route_after_model(state: AgentState) -> Literal["review_tools", "__end__"]:
    """Review model-generated tool calls; otherwise finish the run."""
    messages = state.get("messages", [])
    if messages and isinstance(messages[-1], AIMessage) and messages[-1].tool_calls:
        return "review_tools"
    return cast(Literal["__end__"], END)


# 审核节点先成功提交 checkpoint；高风险 EXA 调用在独立节点中执行。
# 因此执行异常不会把已消费的 resume 和旧 interrupt 一起恢复到 UI。
builder = (
    StateGraph(AgentState, context_schema=Context)
    .add_node("call_model", call_model)
    .add_node("review_tools", review_tool_call)
    .add_node("tools", execute_reviewed_tool)
    .add_edge(START, "call_model")
    .add_conditional_edges("call_model", route_after_model)
    .add_edge("tools", "call_model")
)
graph = builder.compile(name="EXA Search Agent")
