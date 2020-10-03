# Java Util Concurrent

## 基础概念

### 进程

> 进程是一个具有一定独立功能的程序关于某个数据集合的一次运行活动。它是操作系统动态执行的基本单元，在传统的操作系统中，进程既是基本的分配单元，也是基本的执行单元。

进程是一个操作系统运行的独立程序

### 线程

> 通常在一个进程中可以包含若干个线程，当然一个进程中至少有一个线程，不然没有存在的意义。线程可以利用进程所拥有的资源，在引入线程的操作系统中，通常都是把进程作为分配资源的基本单位，而把线程作为独立运行和独立调度的基本单位，由于线程比进程更小，基本上不拥有系统资源，故对它的调度所付出的开销就会小得多，能更高效的提高系统多个程序间并发执行的程度。

轻量级的进程，依附于某个进程，使用某个进程的系统资源

### 并发

如果某个系统支持两个或者多个动作（Action）同时**存在**，那么这个系统就是一个并发系统。
并发的关键是有处理多个任务的能力，**不一定要同时**。

### 并行

如果某个系统支持两个或者多个动作同时执行，那么这个系统就是一个**并行**系统。
并行的关键是有**同时**处理多个任务的能力。

### 并发与并行的区别

![img](juc-note.assets/v2-674f0d37fca4fac1bd2df28a2b78e633_1440w.jpg)

## 线程的虚假唤醒

```java
class Cake {
    private int count = 0;

    public synchronized void increment() throws InterruptedException {
        if (count != 0) {
            wait();
        }
        count++;
        System.out.println(Thread.currentThread().getName() + " : " + count);
        notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        if (count == 0) {
            wait();
        }
        count--;
        System.out.println(Thread.currentThread().getName() + " : " + count);
        notifyAll();
    }
}
```

若存在大于 2 个线程操作资源类，可能存在虚假唤醒的问题。
只要相邻 2 个被唤醒的线程是同一个操作，那么 `count` 将不再交替增减。因为在线程唤醒时，无法指定唤醒线程执行的操作，而线程在唤醒后直接进行增减操作，而没有再次进行判断，从而导致不再交替增减。并且存在死锁的可能(判断条件的问题)。

## java.util.concurrent

> 线程 操作 资源类
> 判断、干活、通知
> 多线程交互中，必须要防止多线程的虚假唤醒，也即（判断只用 while，不能用 if）
> PC 程序计数器

### java.util.concurrent.lock

#### Lock

#### ReentrantLock

可重入锁

```java
lock.lock();
try {
    if (this.count > 0) {
        System.out.printf("%s:\t卖出第 %d 张\t还剩 %d 张%n", Thread.currentThread().getName(), count--, count);
    }
} finally {
    lock.unlock();
}
```

`ReentrantLock`要放在 `try-catch-finally` 块外。

> 在使用阻塞等待获取锁的方式中，必须在try代码块之外，并且在加锁方法与try代码块之间没有任何可能抛出异常的方法调用，避免加锁成功后，在finally中无法解锁。
说明一：如果在lock方法与try代码块之间的方法调用抛出异常，那么无法解锁，造成其它线程无法成功获取锁。
说明二：如果lock方法在try代码块之内，可能由于其它方法抛出异常，导致在finally代码块中，unlock对未加锁的对象解锁，它会调用AQS的tryRelease方法（取决于具体实现类），抛出IllegalMonitorStateException异常。
说明三：在Lock对象的lock方法实现中可能抛出unchecked异常，产生的后果与说明二相同。
