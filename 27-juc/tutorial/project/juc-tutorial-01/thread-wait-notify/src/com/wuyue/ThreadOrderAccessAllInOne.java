package com.wuyue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author DeltaV235
 * @version 1.0
 * @className ThreadOrderAccessAllInOne
 * @description 顺序唤醒线程 在资源类的一个方法中
 * @date 2020/10/4 0:06
 */
public class ThreadOrderAccessAllInOne {
    public static void main(String[] args) {
        AllInOne allInOne = new AllInOne();
        new Thread(() -> {
            allInOne.print(5);
        }, "A").start();

        new Thread(() -> {
            allInOne.print(10);
        }, "B").start();

        new Thread(() -> {
            allInOne.print(15);
        }, "C").start();
    }
}

class AllInOne {
    private int number = 1;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void print(int count) {
        lock.lock();
        try {
            while (count != number * 5) {
                condition.await();
            }
            for (int i = 0; i < count; i++) {
                System.out.printf("%s\t%s%n", Thread.currentThread().getName(), i + 1);
            }
            number++;
            if (number > 3) {
                number = 1;
            }
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
