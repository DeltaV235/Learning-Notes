package com.deltav;

import java.util.concurrent.TimeUnit;

/**
 * Interrupt main thread which block by join method.
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/4/7 23:42
 */
public class InterruptBlockMainThread {
    public static void main(String[] args) {
        Thread threadOne = new Thread(() -> {
            System.out.println("thread one begin run...");
            for (; ; ) {
            }
        });

        Thread mainThread = Thread.currentThread();

        Thread threadTwo = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mainThread.interrupt();

        });

        threadOne.start();

        threadTwo.start();

        try {
            // block main thread to wait thread one finish.
            // thread two will interrupt main thread, then throw a InterruptedException.
            threadOne.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // main thread is running when interrupt occur
        System.out.println("main thread running");
    }
}
