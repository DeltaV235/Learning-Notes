package com.wuyue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 异步回调测试
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2020/11/22 15:59
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future =
                CompletableFuture.runAsync(() -> System.out.println(Thread.currentThread().getName() + "\tCompletableFuture Demo"));
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sleep finish");
        future.get();

        CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
//            int i = 10 / 0;
            return 1024;
        });
        CompletableFuture<Integer> completableFuture = supplyAsync.whenComplete((integer, throwable) -> {
            System.out.println("integer = " + integer);
            System.out.println("throwable = " + throwable);
        }).exceptionally(throwable -> {
            System.out.println("throwable = " + throwable);
            return 404;
        });


        Integer integer = completableFuture.get();
        System.out.println("integer = " + integer);
    }
}
