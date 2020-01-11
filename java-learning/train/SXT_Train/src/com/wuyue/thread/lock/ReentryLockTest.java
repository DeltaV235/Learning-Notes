package com.wuyue.thread.lock;

/**
 * 可重入锁
 */
public class ReentryLockTest {
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

class ReLockTest {
    private boolean isLocked = false;
    private Thread LockedBy = null;
    private int holdCount = 0;

    public synchronized void lock() throws InterruptedException {
        Thread thread = Thread.currentThread();
        while (isLocked && thread != LockedBy)
            wait();
        LockedBy = thread;
        holdCount++;
        isLocked = true;
    }

    public synchronized void unlock() {
        if (Thread.currentThread() == LockedBy) {
            holdCount--;
            if (holdCount == 0) {
                isLocked = false;
                notify();
                LockedBy = null;
            }
        }
    }
}
