# Performation Related

## Cache

### CPU 缓存结构

对于 `X86` 架构 CPU，`L1` 和 `L2` 为每个 core 独享，`L3` 为所有 core 共享。
`L1` 可以分为 `L1D` 和 `L1I`，即 `数据一级缓存` 和 `指令一级缓存`。

一般来说，并行只能提升计算耗时，而不能减少内存读写耗时。

## 分支预测(Branch Prediction)

[深入理解CPU的分支预测(Branch Prediction)模型](https://zhuanlan.zhihu.com/p/22469702)
[why is processing a sorted arrry faster than processing an unsorted array](https://stackoverflow.com/questions/11227809/why-is-processing-a-sorted-array-faster-than-processing-an-unsorted-array)

> 因为分支发生在一个类似if语句的条件判断时,由于判断这个if是否成立会耗费很多个周期,所以CPU先根据分支预测给出的地址把下一条指令送上流水线,而很多个周期后给出的"if 成立与否(是否跳转)"就是你说的"判断是否猜错".
分支预测当然是有意义的,由于程序执行的"局部性原理",分支预测在现代CPU上会达到很高的正确率(>95%),所以猜错虽有代价但概率很小.

**Demo Code**:

**Unsorted Array**:

```java
/**
 * 9.7659674
 * sum = 155184200000
 */
@Benchmark
public void sumUnsortedArray() {
    // Generate data
    int arraySize = 32768;
    int[] data = new int[arraySize];

    Random rnd = new Random(0);
    for (int c = 0; c < arraySize; ++c) {
        data[c] = rnd.nextInt() % 256;
    }

    // Test
    long start = System.nanoTime();
    long sum = 0;

    for (int i = 0; i < 100000; ++i) {
        // Primary loop
        for (int c = 0; c < arraySize; ++c) {
            if (data[c] >= 128) {
                sum += data[c];
            }
        }
    }

    System.out.println((System.nanoTime() - start) / 1000000000.0);
    System.out.println("sum = " + sum);
}
```

**Sorted Array**:

```java
/**
 * 3.5924825
 * sum = 155184200000
*/
@Benchmark
public void sumSortedArray() {
    // Generate data
    int arraySize = 32768;
    int[] data = new int[arraySize];

    Random rnd = new Random(0);
    for (int c = 0; c < arraySize; ++c) {
        data[c] = rnd.nextInt() % 256;
    }

    // !!! With this, the next loop runs faster
    Arrays.sort(data);

    // Test
    long start = System.nanoTime();
    long sum = 0;

    for (int i = 0; i < 100000; ++i) {
        // Primary loop
        for (int c = 0; c < arraySize; ++c) {
            if (data[c] >= 128) {
                sum += data[c];
            }
        }
    }

    System.out.println((System.nanoTime() - start) / 1000000000.0);
    System.out.println("sum = " + sum);
}
```

使用 JMH benchmark 排序及未排序的 array 时的结果如下：

```text
Benchmark                     Mode  Cnt  Score   Error  Units
MyBenchmark.sumSortedArray    avgt   25  1.440 ± 0.011  ms/op
MyBenchmark.sumUnsortedArray  avgt   25  0.372 ± 0.025  ms/op
```

Benchmark result 与预期不符，执行平均时间也与冷启动执行的时间相差多个数量级。原因未知。

执行一次的冷启动执行结果（通过 `System.nanoTime()` 计算执行耗时）

```text
sumSortedArray: 
3.5924825 s

sumUnsortedArray:
9.7659674 s
```

## JMH

JMH(Java Microbenchmark Harness)是由OpenJDK Developer提供的基准测试工具(基准可以理解为比较的基础，我们将这一次性能测试结果作为基准结果，下一次的测试结果将与基准数据进行比较)，它是一种常用的性能测试工具，解决了基准测试中常见的一些问题，本文将针对这些问题介绍如何正确的使用JMH，以及可视化测试结果。

### Create Maven archetype

**command:**

```bash
mvn archetype:generate \
-DinteractiveMode=false \
-DarchetypeGroupId=org.openjdk.jmh \
-DarchetypeArtifactId=jmh-java-benchmark-archetype \
-DgroupId=com.delvav \
-DartifactId=performance-demo \
-Dversion=1.0.0-SNAPSHOT
```

### Run benchmark

```java
public static void main(String[] args) throws RunnerException {
    Options options = new OptionsBuilder()
            .include(MyBenchmark.class.getSimpleName())
            .result("benchmark-result.json")
            .resultFormat(ResultFormatType.JSON)
            .build();
    new Runner(options).run();
}
```

### Sample Code

[JMH Samplle Code](https://github.com/openjdk/jmh/tree/master/jmh-samples)
