package com.wuyue;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 演示类
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2020/11/8 16:32
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "\t finish");
                    countDownLatch.countDown();
                    System.out.println("countDownLatch.getCount() = " + countDownLatch.getCount());
                }
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t main finish");

    }

}
