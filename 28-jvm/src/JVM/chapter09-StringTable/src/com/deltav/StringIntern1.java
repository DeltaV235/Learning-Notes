package com.deltav;

/**
 * Intern method test code.
 * Result:
 * JDK8: false / true
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/8/7 1:41
 */
public class StringIntern1 {
    public static void main(String[] args) {
        // s3 = object in heap
        String s3 = new String("1") + new String("1");
        // s4 = reference in String Pool
        String s4 = "11";
        // s5 = s3 in JDK7/8
        String s5 = s3.intern();

        // false
        System.out.println(s3 == s4);
        // true
        System.out.println(s5 == s4);
    }
}
