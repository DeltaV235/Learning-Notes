package com.deltav;

import java.util.HashSet;

/**
 * Test location of StringTable and test related OOM.
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/7/31 21:45
 */
public class StringTest3 {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }
}
