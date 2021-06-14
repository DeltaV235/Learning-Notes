package com.deltav;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.LongAdder;

/**
 * LongAdder test code. Make add and decrement operations in multi thread to check if LongAdder object is thread-safe.
 * Then check cells array in superclass of LongAdder. Sometimes this array will been instanced.
 * The determination of the instantiated cell array depends on the concurrency level.
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/4/21 0:11
 */
public class LongAdderDemo {
    private static LongAdder longAdder = new LongAdder();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                longAdder.add(3);
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                longAdder.increment();
            }
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                longAdder.decrement();
            }
        });
        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                longAdder.decrement();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("longAdder.sum() = " + longAdder.sum());

        // check cells array in Striped64 object if it has been instanced
        try {
            Field cells = longAdder.getClass().getSuperclass().getDeclaredField("cells");
            cells.setAccessible(true);
            Object obj = cells.get(longAdder);
            if (null != obj) {
                System.out.println("cells = " + obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
