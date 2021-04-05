package com.deltav;

import java.util.concurrent.TimeUnit;

/**
 * 测试 Object.wait() 是否只释放锁对象的监视器锁
 * at P8
 * <p>
 * result: 线程 1 获得 lock1 和 lock2 后，lock1.wait() 只释放 lock1 的监视器锁，并不会释放 lock2，这导致了 线程 2 死锁。
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/2/10 18:36
 */
public class WaitDemo {
    private static final Object lockObject1 = new Object();
    private static final Object lockObject2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {

                System.out.println("try to get lock1");
                synchronized (lockObject1) {
                    System.out.println("get lock1");

                    System.out.println("try to get lock2");
                    synchronized (lockObject2) {
                        System.out.println("get lock2");
                        System.out.println("release lock1 and WAITING");
                        lockObject1.wait();
                    }
                }

            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("try to get lock1");
                synchronized (lockObject1) {
                    System.out.println("get lock1");

                    System.out.println("try to get lock2");
                    synchronized (lockObject2) {
                        System.out.println("get lock2");
                        System.out.println("release lock1 and WAITING");
                        lockObject1.wait();
                    }
                }
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("main thread over");
    }
}
