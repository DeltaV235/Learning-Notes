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

---

**Update 2022-2-27**
在将 JDK 更换为 12 后，benchmark 结果又符合预期了。(•́へ•́╬)

**证实 final:**

在使用 JDK 8 的情况下：

在最后一次 C2 编译后的 Unsorted 方法执行的 native code 如下。

```text
# {method} {0x000000001c2aedc0} 'sumUnsortedArray' '()J' in 'com/deltav/MyBenchmark'
#           [sp+0x50]  (sp of caller)
[Entry Point]
0x0000000003365f20: mov 0x8(%rdx),%r10d
0x0000000003365f24: shl $0x3,%r10
0x0000000003365f28: cmp %r10,%rax
0x0000000003365f2b: jne 0x0000000003185f60  ;   {runtime_call}
0x0000000003365f31: data16 xchg %ax,%ax
0x0000000003365f34: nopl 0x0(%rax,%rax,1)
0x0000000003365f3c: data16 data16 xchg %ax,%ax
[Verified Entry Point]
0x0000000003365f40: mov %eax,-0x6000(%rsp)
0x0000000003365f47: push %rbp
0x0000000003365f48: sub $0x40,%rsp  ;*synchronization entry
                                    ; - com.deltav.MyBenchmark::sumUnsortedArray@-1 (line 102)
0x0000000003365f4c: mov 0x60(%r15),%rbp
0x0000000003365f50: mov %rbp,%r10
0x0000000003365f53: add $0x20010,%r10
0x0000000003365f5a: cmp 0x70(%r15),%r10
0x0000000003365f5e: jae L0007
0x0000000003365f64: mov %r10,0x60(%r15)
0x0000000003365f68: prefetchnta 0x100(%r10)
0x0000000003365f70: movq $0x1,0x0(%rbp)
0x0000000003365f78: prefetchnta 0x140(%r10)
0x0000000003365f80: movl $0xf800016d,0x8(%rbp)  ;   {metadata({type array int})}
0x0000000003365f87: prefetchnta 0x180(%r10)
0x0000000003365f8f: movl $0x8000,0xc(%rbp)
0x0000000003365f96: mov %rbp,%rdi
0x0000000003365f99: add $0x10,%rdi
0x0000000003365f9d: mov $0x4000,%ecx
0x0000000003365fa2: xor %rax,%rax
0x0000000003365fa5: shl $0x3,%rcx
0x0000000003365fa9: rep rex  ; - com.deltav.MyBenchmark::sumUnsortedArray@4 (line 103)
             L0000: mov 0x60(%r15),%r8
0x0000000003365fb0: mov %r8,%r10
0x0000000003365fb3: add $0x18,%r10
0x0000000003365fb7: cmp 0x70(%r15),%r10
0x0000000003365fbb: jae L0008
0x0000000003365fc1: mov %r10,0x60(%r15)
0x0000000003365fc5: prefetchnta 0x100(%r10)
0x0000000003365fcd: mov $0xf800574b,%r10d  ;   {metadata('java/util/concurrent/atomic/AtomicLong')}
0x0000000003365fd3: shl $0x3,%r10
0x0000000003365fd7: mov 0xa8(%r10),%r10
0x0000000003365fde: mov %r10,(%r8)
0x0000000003365fe1: movl $0xf800574b,0x8(%r8)  ;   {metadata('java/util/concurrent/atomic/AtomicLong')}
0x0000000003365fe9: mov %r12d,0xc(%r8)
0x0000000003365fed: mov %r12,0x10(%r8)
             L0001: movabs $0xffffffffffff,%r9
0x0000000003365ffb: xor %r10d,%r10d
0x0000000003365ffe: movabs $0x5deece66d,%rcx
0x0000000003366008: mov %rcx,0x10(%r8)
0x000000000336600c: lock addl $0x0,(%rsp)  ;*synchronization entry
                                           ; - com.deltav.MyBenchmark::sumUnsortedArray@-1 (line 102)
0x0000000003366011: jmp L0004
             L0002: neg %r11d
0x0000000003366016: movzbl %r11b,%r11d
0x000000000336601a: neg %r11d  ;*irem
                               ; - com.deltav.MyBenchmark::sumUnsortedArray@35 (line 107)
             L0003: mov %r11d,0x10(%rbp,%r10,4)  ;*iastore
                                                 ; - com.deltav.MyBenchmark::sumUnsortedArray@36 (line 107)
0x0000000003366022: inc %r10d  ;*iinc
                               ; - com.deltav.MyBenchmark::sumUnsortedArray@37 (line 106)
0x0000000003366025: cmp $0x8000,%r10d
0x000000000336602c: jge L0005  ;*aload_2
                               ; - com.deltav.MyBenchmark::sumUnsortedArray@25 (line 107)
             L0004: mov 0x10(%r8),%rax  ;*invokevirtual compareAndSwapLong
                                        ; - java.util.concurrent.atomic.AtomicLong::compareAndSet@9 (line 147)
                                        ; - java.util.Random::next@32 (line 204)
                                        ; - java.util.Random::nextInt@3 (line 329)
                                        ; - com.deltav.MyBenchmark::sumUnsortedArray@29 (line 107)
0x0000000003366032: mov %rax,%rdi
0x0000000003366035: imul %rcx,%rdi
0x0000000003366039: add $0xb,%rdi  ;*ladd
                                   ; - java.util.Random::next@20 (line 203)
                                   ; - java.util.Random::nextInt@3 (line 329)
                                   ; - com.deltav.MyBenchmark::sumUnsortedArray@29 (line 107)
0x000000000336603d: mov %rdi,%rbx
0x0000000003366040: and %r9,%rbx  ;*land
                                  ; - java.util.Random::next@24 (line 203)
                                  ; - java.util.Random::nextInt@3 (line 329)
                                  ; - com.deltav.MyBenchmark::sumUnsortedArray@29 (line 107)
0x0000000003366043: lock cmpxchg %rbx,0x10(%r8)
0x0000000003366049: sete %r11b
0x000000000336604d: movzbl %r11b,%r11d  ;*invokevirtual compareAndSwapLong
                                        ; - java.util.concurrent.atomic.AtomicLong::compareAndSet@9 (line 147)
                                        ; - java.util.Random::next@32 (line 204)
                                        ; - java.util.Random::nextInt@3 (line 329)
                                        ; - com.deltav.MyBenchmark::sumUnsortedArray@29 (line 107)
0x0000000003366051: test %r11d,%r11d
0x0000000003366054: je L0006  ;*ifeq
                              ; - java.util.Random::next@35 (line 204)
                              ; - java.util.Random::nextInt@3 (line 329)
                              ; - com.deltav.MyBenchmark::sumUnsortedArray@29 (line 107)
0x0000000003366056: shr $0x10,%rdi
0x000000000336605a: mov %edi,%r11d  ;*l2i  ; - java.util.Random::next@45 (line 205)
                                    ; - java.util.Random::nextInt@3 (line 329)
                                    ; - com.deltav.MyBenchmark::sumUnsortedArray@29 (line 107)
0x000000000336605d: test %r11d,%r11d
0x0000000003366060: jl L0002
0x0000000003366062: movzbl %r11b,%r11d  ;*irem
                                        ; - com.deltav.MyBenchmark::sumUnsortedArray@35 (line 107)
0x0000000003366066: jmp L0003
             L0005: xor %eax,%eax
0x000000000336606a: add $0x40,%rsp
0x000000000336606e: pop %rbp
0x000000000336606f: test %eax,-0x24a6075(%rip)  # 0x0000000000ec0000
                                                ;   {poll_return} *** SAFEPOINT POLL ***
0x0000000003366075: ret
             L0006: mov $0xffffff65,%edx
0x000000000336607b: mov %r10d,0x4(%rsp)
0x0000000003366080: mov %rbx,0x10(%rsp)
0x0000000003366085: mov %r11d,0x20(%rsp)
0x000000000336608a: mov %r8,0x28(%rsp)
0x000000000336608f: call 0x00000000031857a0  ; OopMap{rbp=Oop [40]=Oop off=372}
                                             ;*ifeq
                                             ; - java.util.Random::next@35 (line 204)
                                             ; - java.util.Random::nextInt@3 (line 329)
                                             ; - com.deltav.MyBenchmark::sumUnsortedArray@29 (line 107)
                                             ;   {runtime_call}
0x0000000003366094: int3  ;*ifeq
                          ; - java.util.Random::next@35 (line 204)
                          ; - java.util.Random::nextInt@3 (line 329)
                          ; - com.deltav.MyBenchmark::sumUnsortedArray@29 (line 107)
             L0007: mov $0x8000,%r8d
0x000000000336609b: movabs $0x7c0000b68,%rdx  ;   {metadata({type array int})}
0x00000000033660a5: xchg %ax,%ax
0x00000000033660a7: call 0x00000000032450a0  ; OopMap{off=396}
                                             ;*newarray
                                             ; - com.deltav.MyBenchmark::sumUnsortedArray@4 (line 103)
                                             ;   {runtime_call}
0x00000000033660ac: mov %rax,%rbp
0x00000000033660af: jmp L0000  ;*invokespecial <init>
                               ; - java.util.Random::<init>@1 (line 135)
                               ; - com.deltav.MyBenchmark::sumUnsortedArray@12 (line 105)
             L0008: movabs $0x7c002ba58,%rdx  ;   {metadata('java/util/concurrent/atomic/AtomicLong')}
0x00000000033660be: nop
0x00000000033660bf: call 0x00000000032449e0  ; OopMap{rbp=Oop off=420}
                                             ;*new  ; - java.util.Random::<init>@19 (line 137)
                                             ; - com.deltav.MyBenchmark::sumUnsortedArray@12 (line 105)
                                             ;   {runtime_call}
0x00000000033660c4: mov %rax,%r8
0x00000000033660c7: jmp L0001  ;*new
                               ; - java.util.Random::<init>@19 (line 137)
                               ; - com.deltav.MyBenchmark::sumUnsortedArray@12 (line 105)
0x00000000033660cc: mov %rax,%rdx
0x00000000033660cf: jmp L0009
0x00000000033660d1: mov %rax,%rdx  ;*newarray
                                   ; - com.deltav.MyBenchmark::sumUnsortedArray@4 (line 103)
             L0009: add $0x40,%rsp
0x00000000033660d8: pop %rbp
0x00000000033660d9: jmp 0x0000000003245420  ;*aload_2
                                            ; - com.deltav.MyBenchmark::sumUnsortedArray@25 (line 107)
                                            ;   {runtime_call}
0x00000000033660de: hlt
0x00000000033660df: hlt
[Exception Handler]
[Stub Code]
0x00000000033660e0: jmp 0x00000000031aede0  ;   {no_reloc}
[Deopt Handler Code]
0x00000000033660e5: call 0x00000000033660ea
0x00000000033660ea: subq $0x5,(%rsp)
0x00000000033660ef: jmp 0x0000000003187600  ;   {runtime_call}
0x00000000033660f4: hlt
0x00000000033660f5: hlt
0x00000000033660f6: hlt
0x00000000033660f7: hlt
```

可以发现其中没有 114 行后执行累加的循环体。（注释中未出现 114 line）
即如下的循环体。

```java
 for (int i = 0; i < 100000; ++i) {
        // Primary loop
        for (int c = 0; c < arraySize; ++c) {
            if (data[c] >= 128) {
                sum += data[c];
            }
        }
    }
```

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

## JIT

[Java即时编译器原理解析及实践](https://tech.meituan.com/2020/10/22/java-jit-practice-in-meituan.html)
