package com.deltav;

/**
 * Scalar Replace demo code
 * <p>
 * JVM Parameters:
 * -Xmx100m -Xms100m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-EliminateAllocations
 * time cost = 509 ms
 * <p>
 * -Xmx100m -Xms100m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
 * time cost = 6 ms
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/5/9 14:44
 */
public class ScalarReplaceDemo {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            allocate();
        }
        System.out.println("time cost = " + (System.currentTimeMillis() - startTime) + " ms ");
    }

    private static void allocate() {
        User user = new User();
    }

    private static class User {
        private int id = 10;
        private String name = "DeltaV235";
    }
}
