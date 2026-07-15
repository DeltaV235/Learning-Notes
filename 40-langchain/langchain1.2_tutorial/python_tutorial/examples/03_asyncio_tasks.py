"""演示 asyncio 的 TaskGroup、超时、取消和异常传播。"""

import asyncio


async def worker(name: str, delay: float) -> str:
    print(f"{name} 开始")
    try:
        await asyncio.sleep(delay)
    except asyncio.CancelledError:
        print(f"{name} 被取消")
        raise
    print(f"{name} 完成")
    return name


async def task_group_demo() -> None:
    tasks: list[asyncio.Task[str]] = []
    async with asyncio.TaskGroup() as group:
        tasks.append(group.create_task(worker("任务 A", 0.2)))
        tasks.append(group.create_task(worker("任务 B", 0.3)))
    print("TaskGroup 结果：", [task.result() for task in tasks])


async def timeout_demo() -> None:
    try:
        async with asyncio.timeout(0.1):
            await worker("慢任务", 0.5)
    except TimeoutError:
        print("慢任务按预期超时")


async def failing_worker() -> None:
    await asyncio.sleep(0.05)
    raise ValueError("演示异常")


async def exception_demo() -> None:
    try:
        async with asyncio.TaskGroup() as group:
            group.create_task(failing_worker())
            group.create_task(worker("同组任务", 0.5))
    except* ValueError as errors:
        print("捕获异常组：", [str(error) for error in errors.exceptions])


async def main_async() -> None:
    await task_group_demo()
    await timeout_demo()
    await exception_demo()


def main() -> None:
    asyncio.run(main_async())


if __name__ == "__main__":
    main()
