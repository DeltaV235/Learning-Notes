package com.deltav;

import java.io.Serializable;

/**
 * Demo code of checking method area struct.
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/5/28 17:19
 */
public class MethodInnerStructDemo extends Object implements Comparable<String>, Serializable {

    public static final double constant = 10.5;
    // field
    private static String str = "test String in struct demo";
    public int num = 10;

    // constructor

    // method
    public static int test2(int cal) {
        int result = 0;
        try {
            int value = 30;
            result = value / cal;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void test1() {
        int count = 20;
        System.out.println("count = " + count);
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }
}
