package com.deltav;

import java.util.concurrent.TimeUnit;

/**
 * P11 阻塞线程中断测试
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/4/5 17:40
 */
public class WaitNotifyInterrupt {
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("--- start ---");
                synchronized (lock) {
                    lock.wait();
                }
                System.out.println("--- end ---");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();

        TimeUnit.SECONDS.sleep(2);
        System.out.println("--- begin interrupt thread ---");
        thread.interrupt();
        System.out.println("--- end interrupt thread ---");

    }
}
