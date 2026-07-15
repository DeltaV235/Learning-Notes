"""让同一份 AnyIO 代码运行在 asyncio 或 Trio 后端。"""

import argparse

import anyio


async def worker(name: str, delay: float) -> None:
    await anyio.sleep(delay)
    print(f"{name} 完成")


async def main_async() -> None:
    async with anyio.create_task_group() as task_group:
        task_group.start_soon(worker, "任务 A", 0.2)
        task_group.start_soon(worker, "任务 B", 0.1)


def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument(
        "--backend",
        choices=("asyncio", "trio"),
        default="asyncio",
        help="AnyIO 使用的事件循环后端（默认：asyncio）",
    )
    return parser.parse_args()


def main() -> None:
    args = parse_args()
    print(f"当前后端：{args.backend}")
    anyio.run(main_async, backend=args.backend)


if __name__ == "__main__":
    main()
