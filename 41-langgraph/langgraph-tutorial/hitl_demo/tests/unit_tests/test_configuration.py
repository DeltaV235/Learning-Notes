import importlib
from typing import Any

import pytest
from langchain_core.messages import AIMessage, ToolMessage
from langchain_core.runnables import RunnableConfig
from langgraph.checkpoint.memory import InMemorySaver
from langgraph.graph import END, START, StateGraph
from langgraph.pregel import Pregel
from langgraph.types import Command

from agent.graph import (
    AgentState,
    exa_search,
    execute_reviewed_tool,
    graph,
    review_tool_call,
)


def test_graph_contains_review_and_execution_nodes() -> None:
    assert isinstance(graph, Pregel)
    assert {"call_model", "review_tools", "tools"}.issubset(graph.nodes)
    assert exa_search.name == "exa_search"
    assert set(exa_search.args) == {"query"}


def finish_after_rejection(state: AgentState) -> dict[str, Any]:
    return {}


def make_review_graph() -> Pregel:
    """Compile only the deterministic HITL/tool portion for unit tests."""
    return (
        StateGraph(AgentState)
        .add_node("review_tools", review_tool_call)
        .add_node("tools", execute_reviewed_tool)
        .add_node("call_model", finish_after_rejection)
        .add_edge(START, "review_tools")
        .add_edge("tools", END)
        .add_edge("call_model", END)
        .compile(checkpointer=InMemorySaver())
    )


def tool_call_input() -> dict[str, list[AIMessage]]:
    return {
        "messages": [
            AIMessage(
                content="",
                tool_calls=[
                    {
                        "name": "exa_search",
                        "args": {"query": "LangGraph 最新版本"},
                        "id": "call-1",
                        "type": "tool_call",
                    }
                ],
            )
        ]
    }


def thread_config(thread_id: str) -> RunnableConfig:
    return {"configurable": {"thread_id": thread_id}}


def resume_with(value: Any) -> Command[Any]:
    return Command(resume=value)


def test_current_agent_chat_ui_interrupt_can_reject_before_client_creation(
    monkeypatch: pytest.MonkeyPatch,
) -> None:
    monkeypatch.setenv("EXA_API_KEY", "test-key")

    def fail_if_client_is_created(*args: object, **kwargs: object) -> None:
        pytest.fail("EXA client must not be created before approval")

    graph_module = importlib.import_module("agent.graph")
    monkeypatch.setattr(graph_module, "ExaSearchResults", fail_if_client_is_created)
    test_graph = make_review_graph()
    config = thread_config("interrupt-unit-test")
    result = test_graph.invoke(tool_call_input(), config)

    payload = result["__interrupt__"][0].value
    assert payload == {
        "action_requests": [
            {
                "name": "exa_search",
                "args": {"query": "LangGraph 最新版本"},
                "description": "EXA 搜索将访问外部网络并可能消耗 API 配额，请确认查询参数。",
            }
        ],
        "review_configs": [
            {
                "action_name": "exa_search",
                "allowed_decisions": ["approve", "edit", "reject"],
            }
        ],
    }

    resumed = test_graph.invoke(
        resume_with(
            {"decisions": [{"type": "reject", "message": "单元测试拒绝联网搜索"}]}
        ),
        config,
    )
    tool_message = resumed["messages"][-1]
    assert isinstance(tool_message, ToolMessage)
    assert tool_message.status == "error"
    assert "用户拒绝执行 EXA 搜索" in tool_message.text
    assert "单元测试拒绝联网搜索" in tool_message.text


def test_current_agent_chat_ui_edit_decision_updates_exa_query(
    monkeypatch: pytest.MonkeyPatch,
) -> None:
    monkeypatch.setenv("EXA_API_KEY", "test-key")
    captured_request: dict[str, Any] = {}

    class FakeExaSearchResults:
        def __init__(self, **kwargs: Any) -> None:
            captured_request["client_options"] = kwargs

        def invoke(self, request: dict[str, Any]) -> list[dict[str, str]]:
            captured_request["search"] = request
            return [{"title": "LangGraph", "url": "https://langchain.com/langgraph"}]

    graph_module = importlib.import_module("agent.graph")
    monkeypatch.setattr(graph_module, "ExaSearchResults", FakeExaSearchResults)
    test_graph = make_review_graph()
    config = thread_config("edit-interrupt-unit-test")
    interrupted = test_graph.invoke(tool_call_input(), config)
    assert interrupted["__interrupt__"]

    resumed = test_graph.invoke(
        resume_with(
            {
                "decisions": [
                    {
                        "type": "edit",
                        "edited_action": {
                            "name": "exa_search",
                            "args": {"query": "LangGraph Agent Server 官方文档"},
                        },
                    }
                ]
            }
        ),
        config,
    )

    assert captured_request["search"]["query"] == "LangGraph Agent Server 官方文档"
    reviewed_ai_message = next(
        message for message in resumed["messages"] if isinstance(message, AIMessage)
    )
    assert reviewed_ai_message.tool_calls[0]["args"]["query"] == (
        "LangGraph Agent Server 官方文档"
    )
    assert isinstance(resumed["messages"][-1], ToolMessage)


def test_invalid_edit_creates_a_fresh_interrupt_instead_of_raising(
    monkeypatch: pytest.MonkeyPatch,
) -> None:
    monkeypatch.setenv("EXA_API_KEY", "test-key")
    captured_request: dict[str, Any] = {}

    class FakeExaSearchResults:
        def __init__(self, **kwargs: Any) -> None:
            pass

        def invoke(self, request: dict[str, Any]) -> list[dict[str, str]]:
            captured_request.update(request)
            return [{"title": "LangGraph"}]

    graph_module = importlib.import_module("agent.graph")
    monkeypatch.setattr(graph_module, "ExaSearchResults", FakeExaSearchResults)
    test_graph = make_review_graph()
    config = thread_config("invalid-edit-unit-test")
    first = test_graph.invoke(tool_call_input(), config)
    first_interrupt = first["__interrupt__"][0]

    reprompted = test_graph.invoke(
        resume_with(
            {
                "decisions": [
                    {
                        "type": "edit",
                        "edited_action": {
                            "name": "exa_search",
                            "args": {"query": "   "},
                        },
                    }
                ]
            }
        ),
        config,
    )
    second_interrupt = reprompted["__interrupt__"][0]
    assert second_interrupt.id != first_interrupt.id
    assert "上次编辑未通过校验" in second_interrupt.value["action_requests"][0][
        "description"
    ]
    assert "query 不能为空" in second_interrupt.value["action_requests"][0][
        "description"
    ]

    completed = test_graph.invoke(
        resume_with(
            {
                "decisions": [
                    {
                        "type": "edit",
                        "edited_action": {
                            "name": "exa_search",
                            "args": {"query": "修正后的查询"},
                        },
                    }
                ]
            }
        ),
        config,
    )
    assert captured_request["query"] == "修正后的查询"
    assert isinstance(completed["messages"][-1], ToolMessage)


def test_exa_failure_after_edit_does_not_restore_the_review_interrupt(
    monkeypatch: pytest.MonkeyPatch,
) -> None:
    monkeypatch.setenv("EXA_API_KEY", "test-key")

    class FailingExaSearchResults:
        def __init__(self, **kwargs: Any) -> None:
            pass

        def invoke(self, request: dict[str, Any]) -> list[dict[str, str]]:
            raise RuntimeError("simulated EXA failure")

    graph_module = importlib.import_module("agent.graph")
    monkeypatch.setattr(graph_module, "ExaSearchResults", FailingExaSearchResults)
    test_graph = make_review_graph()
    config = thread_config("failed-exa-unit-test")
    interrupted = test_graph.invoke(tool_call_input(), config)
    assert interrupted["__interrupt__"]

    with pytest.raises(RuntimeError, match="simulated EXA failure"):
        test_graph.invoke(
            resume_with(
                {
                    "decisions": [
                        {
                            "type": "edit",
                            "edited_action": {
                                "name": "exa_search",
                                "args": {"query": "会失败的查询"},
                            },
                        }
                    ]
                }
            ),
            config,
        )

    snapshot = test_graph.get_state(config)
    assert snapshot.interrupts == ()
    assert len(snapshot.tasks) == 1
    assert snapshot.tasks[0].name == "tools"
    assert snapshot.tasks[0].interrupts == ()
    assert "simulated EXA failure" in (snapshot.tasks[0].error or "")
