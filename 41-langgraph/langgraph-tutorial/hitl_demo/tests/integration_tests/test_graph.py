import os

import pytest
from langchain_core.messages import AIMessage, HumanMessage, ToolMessage
from langgraph.checkpoint.memory import InMemorySaver
from langgraph.types import Command

from agent.graph import builder

pytestmark = pytest.mark.anyio


@pytest.mark.langsmith
@pytest.mark.skipif(
    os.getenv("RUN_EXTERNAL_INTEGRATION_TESTS") != "1",
    reason="需要显式启用会调用 DeepSeek 和 EXA 的外部集成测试",
)
async def test_agent_can_search_with_exa() -> None:
    graph = builder.compile(checkpointer=InMemorySaver())
    config = {"configurable": {"thread_id": "exa-integration-test"}}
    interrupted = await graph.ainvoke(
        {
            "messages": [
                HumanMessage(content="请使用 EXA 搜索 LangGraph 官方网站并给出 URL。")
            ]
        },
        config,
    )
    payload = interrupted["__interrupt__"][0].value
    assert payload["action_requests"][0]["name"] == "exa_search"
    assert payload["review_configs"][0]["allowed_decisions"] == [
        "approve",
        "edit",
        "reject",
    ]

    result = await graph.ainvoke(
        Command(resume={"decisions": [{"type": "approve"}]}),
        config,
    )
    assert any(isinstance(message, ToolMessage) for message in result["messages"])
    assert isinstance(result["messages"][-1], AIMessage)
