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

---

**2022-2-26 Update**
猜测1：由于在 Benchmark 之前，我将所有 SOUT 去除，所以在循环体后没有调用 `sum` 变量。整个循环体中没有变量逃逸出来，所以经过 Warm Up 后， `JIT` 非常激进的把循环体从代码中删除，并且循环体删除后不会影响代码的表现。
所以出现了如上如此难以解释的测试结果（每次 operation 时间极短且 Unsorted 的执行速度比 Sorted 快）

Benchmark 实际测试的代码，为了 console 干净，把所有 SOUT 注释了。

```java
/**
 * 3.5924825
 * sum = 155184200000
 */
@OperationsPerInvocation(1)
@Benchmark
public void sumSortedArray() {
    // Generate data 2^15
    int arraySize = 32768;
    int[] data = new int[arraySize];

    Random rnd = new Random(0);
    for (int c = 0; c < arraySize; ++c) {
        data[c] = rnd.nextInt() % 256;
    }

    // !!! With this, the next loop runs faster
    Arrays.sort(data);

    // Test
//        long start = System.nanoTime();
    long sum = 0;

    for (int i = 0; i < 100000; ++i) {
        // Primary loop
        for (int c = 0; c < arraySize; ++c) {
            if (data[c] >= 128) {
                sum += data[c];
            }
        }
    }

//        System.out.println((System.nanoTime() - start) / 1000000000.0);
//        System.out.println("sum = " + sum);
}

/**
 * 9.7659674
 * sum = 155184200000
 */
@Benchmark
public void sumUnsortedArray() {
    // Generate data 2^15
    int arraySize = 32768;
    int[] data = new int[arraySize];

    Random rnd = new Random(0);
    for (int c = 0; c < arraySize; ++c) {
        data[c] = rnd.nextInt() % 256;
    }

    // Test
//        long start = System.nanoTime();
    long sum = 0;

    for (int i = 0; i < 100000; ++i) {
        // Primary loop
        for (int c = 0; c < arraySize; ++c) {
            if (data[c] >= 128) {
                sum += data[c];
            }
        }
    }

//        System.out.println((System.nanoTime() - start) / 1000000000.0);
//        System.out.println("sum = " + sum);
}
```

猜测2：由于在 Sorted 方法中，先循环生成伪随机数，并使 int 数组的元素指向该伪随机数。随后再对 `int[]` 进行排序。这种情况下，无法将生成随机数的循环体删除。
而 Unsorted 方法中，无需对 `int[]` 进行排序，在猜想1（数组累加循环体被 JIT 删除）成立的情况下，后续的代码中也未使用到这个 int 数组，所以第一个生成随机数的循环体也被 JIT 删除。
即 Unsorted 方法在预热后，比 Sorted 方法少执行一个随机数生成的循环体和一次数组排序，所以 Unsorted 的平均执行时间比 Sorted 方法少一个数量级。

**证实1：**
将测试代码修改为如下后，测试结果符合预期。测试代码中 `sum` 变量逃逸出测试方法，所以 JIT 无法将循环体优化。
Uncomment SOUT 后也有相同测试结果。

```java
@OperationsPerInvocation(1)
@Benchmark
public long sumSortedArray() {
    // Generate data 2^15
    int arraySize = 32768;
    int[] data = new int[arraySize];

    Random rnd = new Random(0);
    for (int c = 0; c < arraySize; ++c) {
        data[c] = rnd.nextInt() % 256;
    }

    // !!! With this, the next loop runs faster
    Arrays.sort(data);

    // Test
    long sum = 0;

    for (int i = 0; i < 100000; ++i) {
        // Primary loop
        for (int c = 0; c < arraySize; ++c) {
            if (data[c] >= 128) {
                sum += data[c];
            }
        }
    }

    return sum;
}

@Benchmark
public long sumUnsortedArray() {
    // Generate data 2^15
    int arraySize = 32768;
    int[] data = new int[arraySize];

    Random rnd = new Random(0);
    for (int c = 0; c < arraySize; ++c) {
        data[c] = rnd.nextInt() % 256;
    }

    // Test
    long sum = 0;

    for (int i = 0; i < 100000; ++i) {
        // Primary loop
        for (int c = 0; c < arraySize; ++c) {
            if (data[c] >= 128) {
                sum += data[c];
            }
        }
    }

    return sum;
}
```

**证实2：**

修改 Warmup 配置如下，目的在于高频的输出 Warmup 时测试代码的执行效率。

```bash
# Warmup: 10 iterations, 1 s each
@Warmup(iterations = 10, time = 1)
```

**SortedArray:**

```bash
# Benchmark: com.deltav.MyBenchmark.sumSortedArray

# Run progress: 0.00% complete, ETA 00:02:00
# Fork: 1 of 1
# Warmup Iteration   1: 2873.013 ms/op
# Warmup Iteration   2: 2803.866 ms/op
# Warmup Iteration   3: 1.650 ms/op
# Warmup Iteration   4: 1.510 ms/op
# Warmup Iteration   5: 1.517 ms/op
# Warmup Iteration   6: 1.511 ms/op
# Warmup Iteration   7: 1.574 ms/op
# Warmup Iteration   8: 1.552 ms/op
# Warmup Iteration   9: 1.510 ms/op
# Warmup Iteration  10: 1.514 ms/op
```

**UnsortedArray:**

```bash
# Benchmark: com.deltav.MyBenchmark.sumUnsortedArray

# Run progress: 50.00% complete, ETA 00:01:04
# Fork: 1 of 1
# Warmup Iteration   1: 10766.415 ms/op
# Warmup Iteration   2: 10637.132 ms/op
# Warmup Iteration   3: 10867.743 ms/op
# Warmup Iteration   4: 0.426 ms/op
# Warmup Iteration   5: 0.440 ms/op
# Warmup Iteration   6: 0.389 ms/op
# Warmup Iteration   7: 0.382 ms/op
# Warmup Iteration   8: 0.392 ms/op
# Warmup Iteration   9: 0.407 ms/op
# Warmup Iteration  10: 0.410 ms/op
```

可以发现 Warmup 后，执行时间发生了极大的变化。

**证实3：**

使用 `@Fork(value = 1, jvmArgs = {"-Xint"})`，使 microbenchmark 只是用解释器执行测试代码。
Warmup 阶段每次执行的时间相同，且 Sorted 方法比 Unsorted 方法执行的更快（interpreter only 的执行效率比 mixed 和 Compiler Only 慢非常多）

从这次测试中，也可以发现，在非 `-Xint` 模式下，C1 Compiler 会比 C2 Compiler 更早地介入。此前测试中 Sorted 方法 1000 ms 左右的执行时间是已经经过了 C1 优化后的执行效率，1 ms 左右的执行时间是经过 C2 优化后的结果。

```log
# Benchmark: com.deltav.MyBenchmark.sumSortedArray

# Run progress: 0.00% complete, ETA 00:01:30
# Fork: 1 of 1
# Warmup Iteration   1: 51211.331 ms/op
# Warmup Iteration   2: 46183.805 ms/op
# Warmup Iteration   3: 42341.631 ms/op
```

```log
# Run progress: 50.00% complete, ETA 00:04:28
# Fork: 1 of 1
# Warmup Iteration   1: 50527.566 ms/op
# Warmup Iteration   2: 50383.947 ms/op
# Warmup Iteration   3: 50416.355 ms/op
```

```log
Result "com.deltav.MyBenchmark.sumSortedArray":
  41981.788 ±(99.9%) 1088.702 ms/op [Average]
  
Result "com.deltav.MyBenchmark.sumUnsortedArray":
  49175.716 ±(99.9%) 271.523 ms/op [Average]
  
Benchmark                     Mode  Cnt      Score      Error  Units
MyBenchmark.sumSortedArray    avgt   10  41981.788 ± 1088.702  ms/op
MyBenchmark.sumUnsortedArray  avgt   10  49175.716 ±  271.523  ms/op
```

**证实 final:**


---

执行一次的冷启动执行结果（通过 `System.nanoTime()` 计算执行耗时）

```text
sumSortedArray: 
3.5924825 s

sumUnsortedArray:
9.7659674 s
```

## JMH

JMH(Java Microbenchmark Harness)是由OpenJDK Developer提供的基准测试工具(基准可以理解为比较的基础，我们将这一次性能测试结果作为基准结果，下一次的测试结果将与基准数据进行比较)，它是一种常用的性能测试工具，解决了基准测试中常见的一些问题，本文将针对这些问题介绍如何正确的使用JMH，以及可视化测试结果。

**Caution**
在 JDK 12 中 JMH 套件只是添加进了 JDK 源码，为了更方便的 benchmark JDK，并没有包含在 JDK 发布的版本中。所以在需要 JMH 时，仍需要在 Maven 中导入相应的依赖。

> Add a basic suite of microbenchmarks to the **JDK source code**, and make it easy for developers to run existing microbenchmarks and create new ones.

> As far as I understood, this is not a tool for you to create application benchmarks. This is for creating benchmarks for the JDK code, which are not even included in a regular JDK build. As stated on the linked page, “The benchmark suite is not required for regular developer JDK builds; it will be a separate build target.”

**Reference**
[How to use Java 12's Microbenchmark Suite?](https://stackoverflow.com/questions/55243249/how-to-use-java-12s-microbenchmark-suite)

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

## JVM Options - LogCompilation

`-XX:+LogCompilation` 可以将编译活动作为一个日志输出，默认为 hotspot.log，配合 `-XX:LogFile=` 可以将日志重定向至指定的不同的文件中。
编译活动默认不会被记录下来。`-XX:+LogCompilation` 选项必须和 `-XX:+UnlockDiagnosticVMOptions` 选项，一个解锁 JVM 分析的选项，一起使用。
你可以使用 `-XX:+PrintCompilation` 开启详细的分析信息输出，在每一次一个方法被编译时，一条信息将被打印出来。

```bash
-XX:+UnlockDiagnosticVMOptions -XX:+LogCompilation -XX:LogFile=JIT.log
```

在 interpreted mode 下，log 中不会出现 JIT 编译。
