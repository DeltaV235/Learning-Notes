package com.wuyue;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * 练习 IDEA Debugger 功能
 * <p>
 * 练习: https://moqimoqidea.github.io/2017/06/02/IDEA-Debugger/
 *
 * @author moqi On 10/15/20 16:22
 */

public class TestIDEADebugger {

    public static void main(String[] args) {

//        int i = testException();
//        System.out.println("i = " + i);

//        testReload();

        testJavaStream();
    }

    private static int testException() {
        return 10 / 0;
    }

    private static void testReload() {
        int value = new Random().nextInt(1000);
        System.out.println("value = " + value);

        int doubleValue = getDoubleValue(value);
        System.out.println("doubleValue = " + doubleValue);
    }

    private static int getDoubleValue(int i) {
        return i * 2;
//        return i * 100;
    }

    private static void testJavaStream() {
        List<Integer> numberList = Arrays.asList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 0);
        System.out.println("sum is: " +
                numberList.stream()
                        .filter(Objects::nonNull)
                        .distinct()
                        .mapToInt(num -> num * 2)
                        .peek(System.out::println)
                        .skip(2)
                        .limit(4)
                        .sum()
        );
    }

}

