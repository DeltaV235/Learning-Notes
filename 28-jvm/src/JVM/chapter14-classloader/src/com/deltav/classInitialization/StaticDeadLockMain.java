package com.deltav.classInitialization;

import java.util.concurrent.TimeUnit;

/**
 * <clinit> 死锁 demo
 *
 * @author DeltaV235
 * @version 1.0
 */
public class StaticDeadLockMain {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println("thread1 start");
            try {
                Class.forName("com.deltav.classInitialization.StaticA");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("thread2 start");
            try {
                Class.forName("com.deltav.classInitialization.StaticB");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
    }
}

class StaticA {
    static {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Class.forName("com.deltav.classInitialization.StaticB");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Static A is OK!");
    }
}

class StaticB {
    static {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Class.forName("com.deltav.classInitialization.StaticA");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Static B is OK!");
    }
}
