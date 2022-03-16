package com.deltav.instruction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 异常处理
 *
 * @author DeltaV235
 * @version 1.0
 */
public class ExceptionTest {
    public static String func() {
        String str = "hello";
        try {
            return str;
        } finally {
            str = "test";
        }
    }

    public static int func2() {
        int i;
        try {
            i = 10;
            return ++i;
        } finally {
            i = 30;
        }
    }

    public static Inner func3() {
        Inner inner = new Inner();
        try {
            inner.number = 20;
            return inner;
        } finally {
            inner.number = 1000;
        }
    }

    public static void main(String[] args) {
        System.out.println("func() = " + func());
        System.out.println("func2() = " + func2());
        System.out.println("func3() = " + func3().number);
    }

    public void throwZero(int i) {
        if (i == 0) {
            throw new RuntimeException();
        }
    }

    public void throwOne(int i) throws RuntimeException, IOException {
        if (i == 1) {
            throw new RuntimeException("string");
        }
    }

    public void throwArithmetic() {
        int i = 10;
        int j = i / 0;
        System.out.println(j);
    }

    public void tryCatch() {
        int i = 0;
        try {
            File file = new File("test");
            FileInputStream fileInputStream = new FileInputStream(file);

            String info = "hello";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    static class Inner {
        int number;
    }
}
