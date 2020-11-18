package com.wuyue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author DeltaV235
 * @version 1.0
 * @date 2020/11/12 23:52
 */
public class ExecutorDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        ExecutorService executorService2 = Executors.newCachedThreadPool();

        for (int i = 0; i < 40; i++) {
            executorService1.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "\thello world");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
        executorService1.shutdown();
        executorService2.shutdown();
    }
}
