package com.deltav;

/**
 * Test two different way to read/write array. Ensure their efficiency.
 * <p>
 * Code find from page 69 on <i>Java Concurrency in Practice</i>
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/4/18 20:05
 */
public class CacheLineTest {
    static final int LINE_NUMBER = 1024;
    static final int COLUMN_NUMBER = 1024;

    private static void writeArrayWithContinuous() {
        long[][] array = new long[LINE_NUMBER][COLUMN_NUMBER];

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < LINE_NUMBER; i++) {
            for (int j = 0; j < COLUMN_NUMBER; j++) {
                array[i][j] = i * 2 + j;
            }
        }

        long endTime = System.currentTimeMillis();

        System.out.println("cache time: " + (endTime - startTime) + " ms");
    }

    private static void writeArrayWithScatter() {
        long[][] array = new long[LINE_NUMBER][COLUMN_NUMBER];

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < COLUMN_NUMBER; i++) {
            for (int j = 0; j < LINE_NUMBER; j++) {
                array[j][i] = i * 2 + j;
            }
        }

        long endTime = System.currentTimeMillis();

        System.out.println("cache time: " + (endTime - startTime) + " ms");
    }

    public static void main(String[] args) {
        // 5 ms
        writeArrayWithContinuous();
        // 26 ms
        writeArrayWithScatter();
    }
}
