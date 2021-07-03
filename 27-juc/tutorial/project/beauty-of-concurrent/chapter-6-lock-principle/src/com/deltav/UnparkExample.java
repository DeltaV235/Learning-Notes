package com.deltav;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Example code in page 117 of book - the Beauty of Concurrent.
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/7/3 17:28
 */
public class UnparkExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("child thread begin park!");

            LockSupport.park();

            System.out.println("child thread unpark!");
        });

        thread.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main thread begin unpark child thread!");

        LockSupport.unpark(thread);
    }
}
