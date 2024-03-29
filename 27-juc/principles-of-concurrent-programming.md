# Principles of Concurrent Programming

## ThreadLocal Principle

Please refer to *P40*.

Each **Thread** object has two instance fields.

## Principles of LongAccumulator

下文中的 *slot* 值 **Striped64** 中 *cells[]* 数据的元素。

### LongAccumulator.java

```java
// accumulate function decide how to accumulate old value and new value
private final LongBinaryOperator function;

// the identity value equivalent to superclass instance field base.
// In the constructor as a parameter passed into the member variable of the parent class
private final long identity;

/**
 * Creates a new instance using the given accumulator function
 * and identity element.
 * @param accumulatorFunction a side-effect-free function of two arguments
 * @param identity identity (initial value) for the accumulator function
 */
public LongAccumulator(LongBinaryOperator accumulatorFunction,
                        long identity) {
    // input two arguments
    // first one is accumulation method
    this.function = accumulatorFunction;
    // 父类的 base 变量的值会被赋值为 identity 的值
    base = this.identity = identity;
}

/**
 * Updates with the given value.
 *
 * @param x the value
 */
// 执行累加的方法
public void accumulate(long x) {
    Cell[] as; long b, v, r; int m; Cell a;
    // 若父类 Striped64 的 cells[] 已经被实例化，则直接执行 cell 的 cas()
    if ((as = cells) != null ||
        // 若 cells[] 未被初始化
        // 若累加后的值和原值一致，则不进行任何操作
        // 尝试对 base 进行 cas()，若失败则执行代码块
        (r = function.applyAsLong(b = base, x)) != b && !casBase(b, r)) {
        // 初始状态的“未发生竞争”为 true
        boolean uncontended = true;
        // cells 未初始化，则执行 longAccumulate() 进行初始化
        if (as == null || (m = as.length - 1) < 0 ||
            // cells 已初始化，若当前线程的 slot 未初始化，则执行 longAccumulate()，初始化对应的 slot
            (a = as[getProbe() & m]) == null ||
            // cells 以及 slot 已初始化，在累加结果与 slot 中的值不一致时，尝试对 slot 进行 cas，若 cas 成功，则该次累加结束
            // 否则执行 longAccumulate()，并且 uncontended = false，表示在执行 cells 扩容前已经发生了竞争(cells 和 slot 存在，那该判断成立的情况下，longAccumulate() 方法只能执行扩容或 rehashed threadLocalRandomProbe 后重新 cas 或直接在 base 中 cas)
            !(uncontended =
                (r = function.applyAsLong(v = a.value, x)) == v ||
                a.cas(v, r)))
            // 执行 cells 初始化、slot 初始化、cells 扩容、rehasded、重新 slot cas、base case 的操作
            longAccumulate(x, function, uncontended);
    }
}
```

**总结**：

- 若累加后的值与 `base` 相同，则不做任何操作，方法结束
  - 若 `cells[]` 为 null，则尝试对 `base` 进行 cas，若成功则结束该次累加操作
    - 若 casBase 失败，若 `cells[]` 及 `slot` 已完成初始化，则尝试 casCell
      - 若累加后的值与 `slot` 的值一致，则结束操作
      - 否则
        - 若 cas 成功，则累加结束
        - 若 cas 失败，则 call `longAccumulate()`，来扩容 `cells[]` 或重新尝试 `casCell` 和 `casBase`，并且 `uncontended = false`，表示在 call `longAccumulate()` 前 `casCell` 时发生的冲突
    - 若 casBase 失败，`cells[]` 未初始化 或 `slot` 未初始化，则执行 `longAccumulate()` 初始化 `slot`

### Striped64.java

```java
/**
 * Padded variant of AtomicLong supporting only raw accesses plus CAS.
 *
 * JVM intrinsics note: It would be possible to use a release-only
 * form of CAS here, if it were provided.
 */
// cells 的元素
@sun.misc.Contended static final class Cell {
    // volatile 修饰，使每个线程中的该变量都能及时的更新，而非从缓存中读取旧值
    volatile long value;
    Cell(long x) { value = x; }
    final boolean cas(long cmp, long val) {
        // 使用 UNSAFE.casLong 安全替换 value 的值
        return UNSAFE.compareAndSwapLong(this, valueOffset, cmp, val);
    }

    // Unsafe mechanics
    private static final sun.misc.Unsafe UNSAFE;
    // value 在 Cell 类中的内存偏移量
    private static final long valueOffset;
    static {
        try {
            // 获取 UNSAFE 实例
            UNSAFE = sun.misc.Unsafe.getUnsafe();
            Class<?> ak = Cell.class;
            // 获取 value 在 Cell 中的内存偏移量
            valueOffset = UNSAFE.objectFieldOffset
                (ak.getDeclaredField("value"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}

/** Number of CPUS, to place bound on table size */
static final int NCPU = Runtime.getRuntime().availableProcessors();

/**
 * Table of cells. When non-null, size is a power of 2.
 */
transient volatile Cell[] cells;

/**
 * Base value, used mainly when there is no contention, but also as
 * a fallback during table initialization races. Updated via CAS.
 */
transient volatile long base;

/**
 * Spinlock (locked via CAS) used when resizing and/or creating Cells.
 */
transient volatile int cellsBusy;

/**
 * Package-private default constructor
 */
Striped64() {
}

/**
 * CASes the base field.
 */
final boolean casBase(long cmp, long val) {
    return UNSAFE.compareAndSwapLong(this, BASE, cmp, val);
}

/**
 * CASes the cellsBusy field from 0 to 1 to acquire lock.
 */
final boolean casCellsBusy() {
    return UNSAFE.compareAndSwapInt(this, CELLSBUSY, 0, 1);
}

/**
 * Returns the probe value for the current thread.
 * Duplicated from ThreadLocalRandom because of packaging restrictions.
 */
// 获取当前线程中的 threadLocalRandomProbe
static final int getProbe() {
    return UNSAFE.getInt(Thread.currentThread(), PROBE);
}

/**
 * Pseudo-randomly advances and records the given probe value for the
 * given thread.
 * Duplicated from ThreadLocalRandom because of packaging restrictions.
 */
// 伪随机一个新的 threadLocalRandomProbe 并设置回当前线程
static final int advanceProbe(int probe) {
    probe ^= probe << 13;   // xorshift
    probe ^= probe >>> 17;
    probe ^= probe << 5;
    UNSAFE.putInt(Thread.currentThread(), PROBE, probe);
    return probe;
}

/**
 * Handles cases of updates involving initialization, resizing,
 * creating new Cells, and/or contention. See above for
 * explanation. This method suffers the usual non-modularity
 * problems of optimistic retry code, relying on rechecked sets of
 * reads.
 *
 * @param x the value
 * @param fn the update function, or null for add (this convention
 * avoids the need for an extra field or function in LongAdder).
 * @param wasUncontended false if CAS failed before call
 */
 // rehash & new cells[] & new Cell & casCell & casBase & expand cells[]
 // 在 casCell 有冲突的情况下，先 rehash，再重新 casCell，即对不同的 slot 进行 casCell
final void longAccumulate(long x, LongBinaryOperator fn,
                            boolean wasUncontended) {
    int h;
    // 若当前线程未初始化 threadLocalProbe，则先初始化该变量
    if ((h = getProbe()) == 0) {
        ThreadLocalRandom.current(); // force initialization
        h = getProbe();
        wasUncontended = true;
    }
    boolean collide = false;                // True if last slot nonempty slot 非空时为 true
    // 循环直到 accumulate 成功
    for (;;) {
        Cell[] as; Cell a; int n; long v;
        // 1.cells 不为 null，即 cells[] 已初始化；且 cells[] 长度 > 0，即 至少一个 slot 已初始化
        // summary: 初始化 slot
        if ((as = cells) != null && (n = as.length) > 0) {
            // (1)判断 slot 是否为 null
            if ((a = as[(n - 1) & h]) == null) {
                // 判断是否加锁
                if (cellsBusy == 0) {       // Try to attach new Cell
                    // 实例化 slot，并赋值
                    Cell r = new Cell(x);   // Optimistically create
                    // 加锁
                    if (cellsBusy == 0 && casCellsBusy()) {
                        boolean created = false;
                        try {               // Recheck under lock
                            Cell[] rs; int m, j;
                            // 判断 cells 已初始化，对应 slot 未被初始化
                            if ((rs = cells) != null &&
                                (m = rs.length) > 0 &&
                                rs[j = (m - 1) & h] == null) {
                                // 更新 cells 数组元素引用
                                rs[j] = r;
                                created = true;
                            }
                        } finally {
                            cellsBusy = 0;
                        }
                        if (created)
                            break;
                        // slot 已被初始化
                        continue;           // Slot is now non-empty
                    }
                }
                // 初始化 slot 后不立即进行 cells[] 的扩容
                collide = false;
            }
            // (2)在调用 longAccumulate 前，对 slot cas 已经发生了冲突 Continue after rehash，rehash & casCell 依旧失败才会尝试 cells[] 扩容
            else if (!wasUncontended)       // CAS already known to fail
                wasUncontended = true;      // Continue after rehash
            // (4)对 slot cas，成功则结束 accumulate 操作
            else if (a.cas(v = a.value, ((fn == null) ? v + x :
                                            fn.applyAsLong(v, x))))
                break;
            // (5)若 slot 数量大于等于 逻辑 CPU 的数量，则永不扩容 cells[]。或 cells 已扩容，不再执行下方的扩容
            else if (n >= NCPU || cells != as)
                collide = false;            // At max size or stale
            // 防止进行两次连续的 cells 扩容
            else if (!collide)
                collide = true;
            // (6)扩容 cells，cellBusy 加锁
            else if (cellsBusy == 0 && casCellsBusy()) {
                try {
                    // 加锁后，cells 未发生扩容在其他线程中
                    if (cells == as) {      // Expand table unless stale
                        // new 新的 Cell 数组
                        Cell[] rs = new Cell[n << 1];
                        // 迁移旧数组中的 Cell 对象到新数组中
                        for (int i = 0; i < n; ++i)
                            rs[i] = as[i];
                        // this.cells 更新引用地址
                        cells = rs;
                    }
                } finally {
                    // 解锁
                    cellsBusy = 0;
                }
                // 进行 2 次 casCell 失败才会继续扩容
                collide = false;
                continue;                   // Retry with expanded table
            }
            // (3)重新设置当前线程的 threadLocalProbe，rehashed
            h = advanceProbe(h);
        }
        // 2.否则实例化 cells[] 以及 slot
        // 3.判断 cellsBusy 是否加锁 && cells[] 未被其他线程实例化（若 cells[] 被其他线程实例化，则 cells != as） && cellsBusy CAS 加锁成功
        else if (cellsBusy == 0 && cells == as && casCellsBusy()) {
            // 该次初始化成功标志位
            boolean init = false;
            try {                           // Initialize table
                // Double check，确保在 cellsBusy 加锁后，cells[] 仍未被实例化 <==> cells == null，或未发生数组扩容
                if (cells == as) {
                    // 实例化 Cell 数组
                    Cell[] rs = new Cell[2];
                    // 实例化当前 slot，并将 value 赋值
                    // h & 1 <==> threadLocalProbe & cells.length
                    rs[h & 1] = new Cell(x);
                    // 赋值 this.cells
                    cells = rs;
                    // init 成功
                    init = true;
                }
            } finally {
                // 释放 cellsBusy 锁，因为此处已加锁，所以对 cellsBusy 的操作时线程安全的
                cellsBusy = 0;
            }
            // 初始化 cells 及 slot 成功，并对 slot 赋值，累加操作结束
            if (init)
                break;
        }
        // 4.有其他线程在初始化 cells 和 slot 导致 cellsBusy != 0 或 casCellsBusy 失败，或 cells[] 已初始化完成
        // 对 base 尝试 cas
        else if (casBase(v = base, ((fn == null) ? v + x :
                                    fn.applyAsLong(v, x))))
            // casBase 成功后结束该次累加操作
            break;                          // Fall back on using base
    }
}
```

**功能总结**：

1. 初始化 `cells[]` 及 `slot`
2. 初始化 `slot`
3. `cells[]` 扩容
4. `threadLocalProbe` rehash
5. casCell
6. casBase

![Accumulate-of-Striped64](juc-note.assets/Accumulate-of-Striped64.svg)

## LockSupport

This class associates, with each thread that uses it, a permit (in the sense of the Semaphore class). A call to park will return immediately if the permit is available, consuming it in the process; otherwise it may block. A call to unpark makes the permit available, if it was not already available. (Unlike with Semaphores though, permits do not accumulate. There is at most one.)

[Related Offical Ducumentation](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/LockSupport.html)

**Note:**

- `unpark()` method will make specified thread from parameter get a permit.
- `park()` method will consume the permit and return immediately, otherwise current thread will be block.
- Permits do not accumulate, there is at most one permit in one thread.

## AbstractQueuedSynchronizer

### acquire

![AQS-acquire-method-seq-diagram](juc-note.assets/AQS-acquire-method-seq-diagram.png)

```java
public final void acquire(int arg) {
    if (!tryAcquire(arg) &&                            // 若获取独占资源失败，则将当前线程加入 AQS 队列并阻塞
        acquireQueued(addWaiter(Node.EXCLUSIVE), arg)) // 当前线程继续尝试获取独占资源，或 park
        selfInterrupt();                               // 设置当前线程的 interrupt 标志位
}
```

#### tryAquire

需要子类实现，直接调用 `AQS` 将抛出 `UnsupportedOperationException`。（此处为何不使用抽象方法？）
获取独占的资源。

#### addWaiter

将当前线程封装为 `Node`，并添加至 `AQS` 队列尾部。若 `AQS` 未初始化则初始化，并自旋 CAS 设置当前 `AQS` 的 `tail` 的引用指向当前封装的 `Node` 对象。

```java
/**
* Creates and enqueues node for current thread and given mode.
*
* @param mode Node.EXCLUSIVE for exclusive, Node.SHARED for shared
* @return the new node
*/
private Node addWaiter(Node mode) {
    Node node = new Node(Thread.currentThread(), mode);
    // Try the fast path of enq; backup to full enq on failure
    Node pred = tail;
    if (pred != null) {
        node.prev = pred;
        if (compareAndSetTail(pred, node)) {
            pred.next = node;
            return node;
        }
    }
    // 若 AQS 队列未初始化，或 CAS set tail 失败，则使用 enq 入队
    enq(node);
    return node;
}
```

#### enq

enqueue.
若 `AQS` 的 `tail` 为 `null`，则表明 `AQS` 未进行过初始化，则初始化 `AQS` 队列。
若 `AQS` 的 `tail` 不为 `null`，则 CAS 设置 AQS 的 tail 的引用指向。并同时设置要入队的 Node 的 prev 前驱节点和 tail.next tail 的后驱节点。

**方法功能**：

- 初始化 AQS 队列。
- 自旋 CAS 设置 tail。

```java
/**
* Inserts node into queue, initializing if necessary. See picture above.
* @param node the node to insert
* @return node's predecessor
*/
private Node enq(final Node node) {
    for (;;) {
        Node t = tail;
        if (t == null) { // Must initialize
            // 初始化 AQS 队列
            if (compareAndSetHead(new Node()))
                // tail 和 head 指向同一个 Node
                tail = head;
        } else {
            // 自旋设置 node 的前驱节点，并通过 CAS 设置 tail 为 node，直到 CAS 成功。
            node.prev = t;
            if (compareAndSetTail(t, node)) {
                // 若 CAS tail 成功，则设置原 tail 对象的后驱节点
                t.next = node;
                return t;
            }
        }
    }
}
```

#### predecessor

返回 `Node` 的前驱节点。 若前驱节点为 `null` 在抛出 `NullPointerException`。null check 是多余的，但可以帮助 JVM (GC?)

```java
/**
* Returns previous node, or throws NullPointerException if null.
* Use when predecessor cannot be null.  The null check could
* be elided, but is present to help the VM.
*
* @return the predecessor of this node
*/
final Node predecessor() throws NullPointerException {
    Node p = prev;
    if (p == null)
        throw new NullPointerException();
    else
        return p;
}
```

#### acquireQueued

- 当前线程继续尝试获取独占资源，或 park

```java
final boolean acquireQueued(final Node node, int arg) {
    boolean failed = true;
    try {
        boolean interrupted = false;
        for (;;) {
            final Node p = node.predecessor();
            // 若 node 的先驱节点为 head （AQS 队列中下一个执行的 node），则执行 tryAcquire 尝试获取独占资源。
            // 若获取独占资源成功，则 head 指针后移一个节点。
            if (p == head && tryAcquire(arg)) {
                setHead(node); // 此处是否存在线程安全问题
                p.next = null; // help GC
                failed = false;
                return interrupted;
            }
            // 若非 head.next 或获取独占资源失败
            if (shouldParkAfterFailedAcquire(p, node) &&
                parkAndCheckInterrupt())
                interrupted = true;
        }
    } finally {
        if (failed)
            cancelAcquire(node);
    }
}
```

#### shouldParkAfterFailedAcquire

```java
/**
* Checks and updates status for a node that failed to acquire.
* Returns true if thread should block. This is the main signal
* control in all acquire loops.  Requires that pred == node.prev.
*
* @param pred node's predecessor holding status
* @param node the node
* @return {@code true} if thread should block
*/
private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
    int ws = pred.waitStatus;
    if (ws == Node.SIGNAL)
        /*
            * This node has already set status asking a release
            * to signal it, so it can safely park.
            */
        return true;
    if (ws > 0) {
        /*
            * Predecessor was cancelled. Skip over predecessors and
            * indicate retry.
            */
        do {
            node.prev = pred = pred.prev;
        } while (pred.waitStatus > 0);
        pred.next = node;
    } else {
        /*
            * waitStatus must be 0 or PROPAGATE.  Indicate that we
            * need a signal, but don't park yet.  Caller will need to
            * retry to make sure it cannot acquire before parking.
            */
        compareAndSetWaitStatus(pred, ws, Node.SIGNAL);
    }
    return false;
}
```

### release

```java

```

## AbstractOwableSynchronizer

- 用于存放独占线程

```java
/*
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/publicdomain/zero/1.0/
 */

package java.util.concurrent.locks;

/**
 * A synchronizer that may be exclusively owned by a thread.  This
 * class provides a basis for creating locks and related synchronizers
 * that may entail a notion of ownership.  The
 * {@code AbstractOwnableSynchronizer} class itself does not manage or
 * use this information. However, subclasses and tools may use
 * appropriately maintained values to help control and monitor access
 * and provide diagnostics.
 *
 * @since 1.6
 * @author Doug Lea
 */
public abstract class AbstractOwnableSynchronizer
    implements java.io.Serializable {

    /** Use serial ID even though all fields transient. */
    private static final long serialVersionUID = 3737899427754241961L;

    /**
     * Empty constructor for use by subclasses.
     */
    protected AbstractOwnableSynchronizer() { }

    /**
     * The current owner of exclusive mode synchronization.
     */
    // 独占模式下，资源持有线程
    private transient Thread exclusiveOwnerThread;

    /**
     * Sets the thread that currently owns exclusive access.
     * A {@code null} argument indicates that no thread owns access.
     * This method does not otherwise impose any synchronization or
     * {@code volatile} field accesses.
     * @param thread the owner thread
     */
    // 设置独占 Owner 线程
    protected final void setExclusiveOwnerThread(Thread thread) {
        exclusiveOwnerThread = thread;
    }

    /**
     * Returns the thread last set by {@code setExclusiveOwnerThread},
     * or {@code null} if never set.  This method does not otherwise
     * impose any synchronization or {@code volatile} field accesses.
     * @return the owner thread
     */
    protected final Thread getExclusiveOwnerThread() {
        return exclusiveOwnerThread;
    }
}

```

## Interrupt

若在线程 start 前执行 thread.interrupt()，则其中断标志位不会置位。
