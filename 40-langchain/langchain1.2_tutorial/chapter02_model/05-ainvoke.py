import asyncio
import time
from asyncio import Task

from dotenv import load_dotenv
from langchain.chat_models.base import init_chat_model


async def worker(name: str, delay: float = 0.0):
    await asyncio.sleep(delay)
    print(f"{name} worker finished")


async def main():
    load_dotenv(override=True)

    model = init_chat_model(
        model="deepseek:deepseek-v4-flash",
    )
    message = [
        {"role": "system", "content": "你是一位 Python 专家，解释用户提出的关于 Python 的问题"},
        {"role": "user", "content": "... 是什么语法"}
    ]

    start_time = time.perf_counter()
    tasks: list[Task] = []
    async with asyncio.TaskGroup() as group:
        tasks.append(group.create_task(model.ainvoke(message)))
        tasks.append(group.create_task(asyncio.sleep(0.1)))
        tasks.append(group.create_task(worker("demo-ainvoke", 3)))

    print(tasks[0].result())
    print(f"总耗时：{time.perf_counter() - start_time:.2f} s")


if __name__ == "__main__":
    asyncio.run(main())
