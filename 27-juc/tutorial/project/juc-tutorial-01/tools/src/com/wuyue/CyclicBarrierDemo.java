package com.wuyue;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 演示类
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2020/11/8 17:22
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(6, new Runnable() {
            @Override
            public void run() {
                System.out.println("total count has arrive threshold");
            }
        });

        for (int i = 0; i < 6; i++) {
            final int temp = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 3; j++) {
                        System.out.println(Thread.currentThread().getName() + "\t" + temp);
                        try {
                            cyclicBarrier.await();
                            System.out.println(Thread.currentThread().getName() + "\twaiting finish");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, String.valueOf(i)).start();
        }
    }
}
