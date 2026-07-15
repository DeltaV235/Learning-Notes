"""集中演示 Python 常用线程同步与通信工具。"""

from concurrent.futures import Future, ThreadPoolExecutor
from queue import Queue
from threading import (
    Barrier,
    BoundedSemaphore,
    Condition,
    Event,
    Lock,
    RLock,
    Semaphore,
    current_thread,
    local,
)
from time import sleep
from typing import Callable


def demo_lock_and_rlock() -> None:
    shared: list[str] = []
    lock = Lock()
    with lock:
        shared.append("Lock 保护的写操作")

    rlock = RLock()

    def inner() -> None:
        with rlock:
            shared.append("RLock 被同一线程再次获取")

    with rlock:
        inner()

    print("Lock / RLock：", shared)


def demo_condition() -> None:
    condition = Condition()
    items: list[str] = []

    def consumer() -> str:
        with condition:
            condition.wait_for(lambda: bool(items))
            return items.pop(0)

    def producer() -> None:
        sleep(0.05)
        with condition:
            items.append("消息")
            condition.notify()

    with ThreadPoolExecutor(max_workers=2) as executor:
        consumed = executor.submit(consumer)
        executor.submit(producer).result()
        print("Condition：", consumed.result())


def demo_semaphores() -> None:
    semaphore = Semaphore(2)
    state_lock = Lock()
    active = 0
    peak_active = 0

    def limited_task() -> None:
        nonlocal active, peak_active
        with semaphore:
            with state_lock:
                active += 1
                peak_active = max(peak_active, active)
            sleep(0.03)
            with state_lock:
                active -= 1

    with ThreadPoolExecutor(max_workers=5) as executor:
        list(executor.map(lambda _: limited_task(), range(5)))

    assert peak_active == 2
    print("Semaphore 最大并发：", peak_active)

    bounded = BoundedSemaphore(1)
    bounded.acquire()
    bounded.release()
    try:
        bounded.release()
    except ValueError:
        print("BoundedSemaphore：发现过度释放")


def demo_event_and_barrier() -> None:
    ready = Event()

    def wait_until_ready() -> str:
        ready.wait()
        return "收到 Event 通知"

    with ThreadPoolExecutor(max_workers=1) as executor:
        notification = executor.submit(wait_until_ready)
        sleep(0.03)
        ready.set()
        print("Event：", notification.result())

    barrier = Barrier(3)

    def reach_barrier(worker_id: int) -> int:
        sleep(worker_id * 0.01)
        return barrier.wait()

    with ThreadPoolExecutor(max_workers=3) as executor:
        tokens = list(executor.map(reach_barrier, range(3)))

    assert sorted(tokens) == [0, 1, 2]
    print("Barrier：3 个线程均已到达，返回值", tokens)


def demo_queue() -> None:
    tasks: Queue[int | None] = Queue()
    processed: list[int] = []
    processed_lock = Lock()

    def consumer() -> None:
        while True:
            item = tasks.get()
            try:
                if item is None:
                    return
                with processed_lock:
                    processed.append(item * item)
            finally:
                tasks.task_done()

    with ThreadPoolExecutor(max_workers=2) as executor:
        consumers = [executor.submit(consumer) for _ in range(2)]
        for value in range(5):
            tasks.put(value)
        for _ in consumers:
            tasks.put(None)
        tasks.join()
        for consumer_future in consumers:
            consumer_future.result()

    print("Queue：", sorted(processed))


def demo_future() -> None:
    def divide(left: int, right: int) -> float:
        return left / right

    with ThreadPoolExecutor(max_workers=2) as executor:
        successful: Future[float] = executor.submit(divide, 8, 2)
        failed: Future[float] = executor.submit(divide, 1, 0)
        print("Future 结果：", successful.result())
        print("Future 异常：", type(failed.exception()).__name__)


def demo_thread_local() -> None:
    context = local()

    def handle_request(request_id: str) -> tuple[str, str]:
        context.request_id = request_id
        sleep(0.02)
        return current_thread().name, context.request_id

    with ThreadPoolExecutor(max_workers=2, thread_name_prefix="worker") as executor:
        results = list(executor.map(handle_request, ("req-A", "req-B")))

    print("threading.local：", results)


class AtomicCounter:
    """用 Lock 组合出教学用的线程安全计数器。"""

    def __init__(self) -> None:
        self._value = 0
        self._lock = Lock()

    def increment(self) -> None:
        with self._lock:
            self._value += 1

    @property
    def value(self) -> int:
        with self._lock:
            return self._value


def demo_atomic_counter() -> None:
    counter = AtomicCounter()
    callbacks: list[Callable[[], None]] = [counter.increment] * 100
    with ThreadPoolExecutor(max_workers=8) as executor:
        list(executor.map(lambda callback: callback(), callbacks))
    assert counter.value == 100
    print("Lock 保护的计数器：", counter.value)


def main() -> None:
    demo_lock_and_rlock()
    demo_condition()
    demo_semaphores()
    demo_event_and_barrier()
    demo_queue()
    demo_future()
    demo_thread_local()
    demo_atomic_counter()


if __name__ == "__main__":
    main()
