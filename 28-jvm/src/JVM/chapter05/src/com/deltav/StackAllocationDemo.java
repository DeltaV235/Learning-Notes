package com.deltav;

import java.util.concurrent.TimeUnit;

/**
 * Stack allocate demo code
 * <p>
 * JVM Parameters:
 * -Xms128m -Xmx128m -XX:-DoEscapeAnalysis -XX:+PrintGC
 * time cost = 6509 ms
 * <p>
 * -Xms128m -Xmx128m -XX:+DoEscapeAnalysis -XX:+PrintGC
 * time cost = 7 ms
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/5/9 14:24
 */
public class StackAllocationDemo {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            allocate();
        }
        System.out.println("time cost = " + (System.currentTimeMillis() - startTime) + " ms ");

        try {
            TimeUnit.MINUTES.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void allocate() {
        User user = new User();
    }

    private static class User {
    }
}
