package com.deltav;

/**
 * String Intern method test code.
 * JVM version is JDK8.
 * Result is true / true.
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/8/7 1:35
 */
public class StringExer1 {
    public static void main(String[] args) {
//        String x = "ab";
        // create a object in heap, but not in String Pool
        String s = new String("a") + new String("b"); // new String("ab");

        // In JDK8, s2 = s
        String s2 = s.intern();

        // true, "ab" = reference of s variable
        System.out.println(s2 == "ab");
        // true
        System.out.println(s == "ab");
    }
}
