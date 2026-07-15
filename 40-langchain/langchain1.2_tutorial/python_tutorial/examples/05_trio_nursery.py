"""演示 Trio nursery 的结构化并发和失败传播。"""

import trio


async def worker(name: str, delay: float, should_fail: bool = False) -> None:
    print(f"{name} 开始")
    try:
        await trio.sleep(delay)
        if should_fail:
            raise ValueError(f"{name} 发生错误")
        print(f"{name} 完成")
    finally:
        print(f"{name} 清理资源")


async def main_async() -> None:
    try:
        async with trio.open_nursery() as nursery:
            nursery.start_soon(worker, "普通任务", 0.5)
            nursery.start_soon(worker, "失败任务", 0.1, True)
    except* ValueError as errors:
        print("nursery 传播的异常：", [str(error) for error in errors.exceptions])
        print("预期：一个子任务失败后，nursery 会取消仍在运行的兄弟任务。")


def main() -> None:
    trio.run(main_async)


if __name__ == "__main__":
    main()
