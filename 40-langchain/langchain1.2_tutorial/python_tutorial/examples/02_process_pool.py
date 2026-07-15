"""使用进程池并行处理 CPU 密集型任务。"""

from concurrent.futures import ProcessPoolExecutor


def count_primes(limit: int) -> int:
    """返回小于 limit 的质数数量。函数必须可被子进程导入。"""
    count = 0
    for candidate in range(2, limit):
        is_prime = True
        divisor = 2
        while divisor * divisor <= candidate:
            if candidate % divisor == 0:
                is_prime = False
                break
            divisor += 1
        if is_prime:
            count += 1
    return count


def main() -> None:
    limits = [20_000, 22_000]
    with ProcessPoolExecutor(max_workers=2) as executor:
        results = list(executor.map(count_primes, limits))

    print(dict(zip(limits, results, strict=True)))
    print("预期：两个计算任务由不同进程执行，不共享同一个 GIL。")


if __name__ == "__main__":
    # macOS、Windows 以及使用 spawn 启动方式时必须保留入口保护。
    main()
