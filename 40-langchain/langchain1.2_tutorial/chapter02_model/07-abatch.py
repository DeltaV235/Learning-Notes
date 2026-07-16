import asyncio
import time
from asyncio import Task

from dotenv import load_dotenv
from langchain.chat_models.base import init_chat_model
from rich import print as rprint


async def worker(name: str, delay: float = 0.0):
    await asyncio.sleep(delay)
    print(f"{name} worker finished")


async def main():
    load_dotenv(override=True)

    model = init_chat_model(
        model="deepseek:deepseek-v4-flash",
    )
    message = [
        "Hello World!",
        "上海在哪里？",
        "今天是几月几日？"
    ]

    start_time = time.perf_counter()
    tasks: list[Task] = [
        asyncio.create_task(model.abatch(message)),
        asyncio.create_task(worker("task-01", 3)),
        asyncio.create_task(worker("task-02", 10))
    ]

    await tasks[0]
    for response in tasks[0].result():
        rprint(response.content)

    await asyncio.gather(*tasks)

    print(f"总耗时：{time.perf_counter() - start_time:.2f} s")


if __name__ == "__main__":
    asyncio.run(main())
