# LangChain 1.2 教程

本项目使用 Python 3.13 和 Jupyter Notebook 记录 LangChain 1.2 及相关 Python 基础知识。

## Python 并发与异步教程

`python_tutorial/` 面向有 Java 经验的 Python 初学者，建议按以下顺序学习：

1. [同步与并发基础](python_tutorial/01-sync-and-concurrency-basics.ipynb)：同步、异步、阻塞、并发与并行。
2. [线程、同步工具、进程与 GIL](python_tutorial/02-threads-processes-and-gil.ipynb)：锁、条件变量、信号量、线程通信、线程池和多进程。
3. [asyncio 基础](python_tutorial/03-asyncio-fundamentals.ipynb)：协程、事件循环、Task、超时与取消。
4. [AnyIO 与 Trio](python_tutorial/04-anyio-and-trio.ipynb)：后端抽象、nursery 与结构化并发。

安装或同步环境：

```bash
uv sync
```

启动 Notebook：

```bash
uv run jupyter notebook
```

每个脚本都可以独立运行，例如：

```bash
uv run python python_tutorial/examples/01_sync_and_threads.py
uv run python python_tutorial/examples/02_process_pool.py
uv run python python_tutorial/examples/03_asyncio_tasks.py
uv run python python_tutorial/examples/04_anyio_backends.py --backend asyncio
uv run python python_tutorial/examples/04_anyio_backends.py --backend trio
uv run python python_tutorial/examples/05_trio_nursery.py
uv run python python_tutorial/examples/06_thread_synchronization.py
uv run python python_tutorial/examples/07_async_with_taskgroup.py
```

所有教程示例均为离线示例，不需要外部 API 或网络连接。
