"""比较同步执行与线程池并发执行 I/O 型任务。"""

from concurrent.futures import ThreadPoolExecutor
from time import perf_counter, sleep


def simulated_io(task_id: int, delay: float = 0.4) -> str:
    """用 sleep 模拟等待网络或磁盘响应。"""
    sleep(delay)
    return f"任务 {task_id} 完成"


def run_sequentially(task_count: int) -> list[str]:
    return [simulated_io(task_id) for task_id in range(task_count)]


def run_with_threads(task_count: int) -> list[str]:
    with ThreadPoolExecutor(max_workers=task_count) as executor:
        return list(executor.map(simulated_io, range(task_count)))


def main() -> None:
    task_count = 3

    started = perf_counter()
    sequential_results = run_sequentially(task_count)
    sequential_elapsed = perf_counter() - started

    started = perf_counter()
    threaded_results = run_with_threads(task_count)
    threaded_elapsed = perf_counter() - started

    assert sequential_results == threaded_results
    print("同步结果：", sequential_results)
    print(f"同步耗时：{sequential_elapsed:.2f} 秒")
    print(f"线程池耗时：{threaded_elapsed:.2f} 秒")
    print("预期：线程池版本更快，因为等待期间其他线程可以继续工作。")


if __name__ == "__main__":
    main()
