package com.deltav;

/**
 * @author DeltaV235
 * @version 1.0
 * @date 2021/8/7 1:50
 */
public class StringExer2 {
    public static void main(String[] args) {
        // "ab" already in String Pool, and s1 in heap
        String s1 = new String("ab");

        s1.intern();
        String s2 = "ab";
        // JDK6/7/8: false
        System.out.println(s1 == s2);
    }
}
