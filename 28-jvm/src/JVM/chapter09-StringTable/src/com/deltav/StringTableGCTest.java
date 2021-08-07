package com.deltav;

/**
 * Test GC for String Table.
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/8/7 12:50
 */
public class StringTableGCTest {
    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            String.valueOf(i).intern();
        }
    }
}
