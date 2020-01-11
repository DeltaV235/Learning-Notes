package com.wuyue.thread.lock;

/**
 * 不可重入锁
 */
public class UnReentryLockTest {
    private static ReLockTest lock = new ReLockTest();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " I have lock now");
                test();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }).start();
    }

    public static void test() throws InterruptedException {
        lock.lock();
        System.out.println("Hello World!");
        lock.unlock();
    }
}

class LockTest {
    private boolean isLocked = false;

    public synchronized void lock() throws InterruptedException {
        while (isLocked)
            wait();
        isLocked = true;
    }

    public synchronized void unlock() {
        isLocked = false;
        notify();
    }
}
