package com.wuyue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author DeltaV235
 * @version 1.0
 * @className ThreadLockDemo
 * @description
 * @date 2020/10/3 15:08
 */
public class ThreadLockDemo {
    public static void main(String[] args) {
        DiffCake diffCake = new DiffCake();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    diffCake.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    diffCake.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    diffCake.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "D").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    diffCake.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();
    }
}

class DiffCake {
    private int count = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            while (this.count != 0) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + " count: " + ++count);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (this.count == 0) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + " count: " + --count);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
