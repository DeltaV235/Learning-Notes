"""演示 async with 与 TaskGroup 如何共同管理资源和子任务。"""

import asyncio
from collections.abc import Sequence


class DemoError(Exception):
    """教程中用于触发 TaskGroup 失败传播的业务异常。"""


class AsyncConnection:
    """用短暂 sleep 模拟需要异步打开和关闭的连接。"""

    def __init__(self, events: list[str]) -> None:
        self.events = events
        self.is_open = False

    async def __aenter__(self) -> "AsyncConnection":
        self.events.append("connection:opening")
        await asyncio.sleep(0.02)
        self.is_open = True
        self.events.append("connection:opened")
        return self

    async def __aexit__(self, exc_type, exc_value, traceback) -> bool:
        exception_name = exc_type.__name__ if exc_type else "None"
        self.events.append(f"connection:closing:{exception_name}")
        await asyncio.sleep(0.02)
        self.is_open = False
        self.events.append("connection:closed")
        return False

    async def query(self, name: str, delay: float) -> str:
        if not self.is_open:
            raise RuntimeError("连接尚未打开")
        self.events.append(f"{name}:started")
        await asyncio.sleep(delay)
        self.events.append(f"{name}:finished")
        return f"{name} result"


def index_of(events: Sequence[str], value: str) -> int:
    return events.index(value)


async def successful_demo() -> None:
    events: list[str] = []
    tasks: list[asyncio.Task[str]] = []

    async with AsyncConnection(events) as connection:
        async with asyncio.TaskGroup() as group:
            tasks.append(group.create_task(connection.query("query-A", 0.04)))
            tasks.append(group.create_task(connection.query("query-B", 0.02)))

        # 离开 TaskGroup 后，所有任务已经结束，才能安全读取 result()。
        results = [task.result() for task in tasks]

    assert results == ["query-A result", "query-B result"]
    assert index_of(events, "connection:opened") < index_of(events, "query-A:started")
    assert index_of(events, "query-A:finished") < index_of(
        events, "connection:closing:None"
    )
    assert index_of(events, "query-B:finished") < index_of(
        events, "connection:closing:None"
    )
    assert events[-1] == "connection:closed"

    print("正常流程：")
    print("  结果：", results)
    print("  生命周期：", " -> ".join(events))


async def failing_worker(events: list[str]) -> None:
    events.append("failing:started")
    await asyncio.sleep(0.02)
    events.append("failing:raised")
    raise DemoError("模拟任务失败")


async def slow_worker(events: list[str]) -> None:
    events.append("slow:started")
    try:
        await asyncio.sleep(0.5)
        events.append("slow:finished")
    finally:
        # TaskGroup 取消兄弟任务后，finally 仍会执行。
        events.append("slow:cleanup")


async def failing_demo() -> None:
    events: list[str] = []

    try:
        async with AsyncConnection(events):
            async with asyncio.TaskGroup() as group:
                group.create_task(failing_worker(events))
                group.create_task(slow_worker(events))
    except* DemoError as errors:
        events.append(f"caught:{len(errors.exceptions)}")

    assert "slow:finished" not in events
    assert index_of(events, "failing:raised") < index_of(events, "slow:cleanup")
    assert index_of(events, "slow:cleanup") < index_of(
        events, "connection:closing:ExceptionGroup"
    )
    assert index_of(events, "connection:closed") < index_of(events, "caught:1")

    print("失败流程：")
    print("  生命周期：", " -> ".join(events))
    print("  预期：失败任务触发兄弟任务取消，资源最后关闭。")


async def main_async() -> None:
    await successful_demo()
    await failing_demo()


def main() -> None:
    asyncio.run(main_async())


if __name__ == "__main__":
    main()
