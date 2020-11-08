package com.wuyue;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author DeltaV235
 * @version 1.0
 * @className CallableDemo
 * @description Callable Test Demo
 * @date 2020/10/11 20:05
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> integerFutureTask = new FutureTask<>(new CallableThread());
        Thread thread = new Thread(integerFutureTask, "A");
        Thread thread2 = new Thread(integerFutureTask, "B");
        System.out.println("thread start");
        thread.start();
        TimeUnit.SECONDS.sleep(10);
        thread2.start();
        System.out.println("integerFutureTask.get() = " + integerFutureTask.get());
        System.out.println(Thread.currentThread().getName() + ": finished");
    }

    static class CallableThread implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {

            try {
                System.out.println("into callable thread");
                TimeUnit.SECONDS.sleep(5);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2048;
        }
    }
}
