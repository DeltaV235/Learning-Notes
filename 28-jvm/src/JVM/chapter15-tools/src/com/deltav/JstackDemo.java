package com.deltav;

import java.util.concurrent.TimeUnit;

/**
 * Deadlock demo for testing jstack command line tool
 *
 * @author DeltaV235
 * @version 1.0
 */
public class JstackDemo {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock1) {
                System.out.println("thread1 get lock1");

                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("thread1 try to get lock2");
                synchronized (lock2) {
                    System.out.println("thread1 get lock2");
                }

                System.out.println("thread1 END");
            }
        }).start();

        new Thread(() -> {
            synchronized (lock2) {
                System.out.println("thread2 get lock2");

                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("thread2 try to get lock1");
                synchronized (lock1) {
                    System.out.println("thread2 get lock1");
                }

                System.out.println("thread2 END");
            }
        }).start();

        System.out.println("main thread END");
    }
}
