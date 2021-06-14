package com.deltav;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.LongAccumulator;

/**
 * demo code to test LongAccumulator. The aim is same as LongAdder demo code. {@link LongAdderDemo}
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/6/14 22:14
 */
public class LongAccumulatorDemo {
    private static final LongAccumulator longAccumulator = new LongAccumulator((left, right) -> left * right, 2);

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                longAccumulator.accumulate(2);
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                longAccumulator.accumulate(2);
            }
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                longAccumulator.accumulate(2);
            }
        });
        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                longAccumulator.accumulate(-1);
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

        System.out.println("longAccumulator.intValue() = " + longAccumulator.intValue());

        // check cells array in Striped64 object if it has been instanced
        try {
            Field cells = longAccumulator.getClass().getSuperclass().getDeclaredField("cells");
            cells.setAccessible(true);
            Object obj = cells.get(longAccumulator);
            if (null != obj) {
                System.out.println("cells = " + obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
