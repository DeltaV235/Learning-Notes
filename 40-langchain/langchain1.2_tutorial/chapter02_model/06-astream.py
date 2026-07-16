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
    response = model.astream(input=message)
    start_time = time.perf_counter()
    tasks: list[Task] = [
        asyncio.create_task(worker("task-01", 0.1)),
        asyncio.create_task(worker("task-02", 15))
    ]

    async for chunk in response:
        content = chunk.content if hasattr(chunk, "content") else str(chunk)
        print(content, end="", flush=True)

    await asyncio.gather(*tasks, return_exceptions=True)
    print(f"\n总耗时：{time.perf_counter() - start_time:.2f} s")


if __name__ == "__main__":
    asyncio.run(main())
