package com.deltav;

import java.util.concurrent.TimeUnit;

/**
 * Test reference variable in multi-thread condition.
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/7/3 14:59
 */
public class ReferenceTest {
    private static Object ref = new Object();

    public static void main(String[] args) {
        Object localRef = ref;
        System.out.println("localRef = " + localRef);
        new Thread(() -> ref = new Object()).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("localRef = " + localRef);
            System.out.println("ref = " + ref);
            try {
                TimeUnit.MILLISECONDS.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
