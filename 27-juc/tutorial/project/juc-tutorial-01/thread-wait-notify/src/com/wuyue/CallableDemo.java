package com.wuyue;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

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
        new Thread(integerFutureTask, "A").start();
        System.out.println("integerFutureTask.get() = " + integerFutureTask.get());
    }

    static class CallableThread implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("hello callable");
            return 2048;
        }
    }
}
