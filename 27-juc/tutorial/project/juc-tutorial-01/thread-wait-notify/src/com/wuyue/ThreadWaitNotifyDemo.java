package com.wuyue;

/**
 * @author DeltaV235
 * @version 1.0
 * @className ThreadWaitNotifyDemo
 * @description 生产者 消费者 模型
 * @date 2020/9/8 0:20
 */
public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {
        Cake cake = new Cake();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    cake.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    cake.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    cake.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    cake.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}

class Cake {
    private int count = 0;

    public synchronized void increment() throws InterruptedException {
        while (count != 0) {
            wait();
        }
        count++;
        System.out.println(Thread.currentThread().getName() + " : " + count);
        notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while (count == 0) {
            wait();
        }
        count--;
        System.out.println(Thread.currentThread().getName() + " : " + count);
        notifyAll();
    }
}
