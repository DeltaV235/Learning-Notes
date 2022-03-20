package com.deltav.loadcase;

import org.junit.Test;

import java.util.Random;

interface CompareA {
    public static final Object obj = new Object() {
        {
            System.out.println("interface <clinit> has been invoked...");
        }
    };

    public static final int NUM = 1;
    int NUM2 = new Random().nextInt(10);
}

/**
 * 当使用类、接口的静态字段时(final修饰特殊考虑)，比如，使用 `getstatic` 或者 `putstatic` 指令。(对应访问变量、赋值变量操作)
 *
 * @author DeltaV235
 * @version 1.0
 */
public class ActiveLoad2 {
    @Test
    public void callStaticField() {
        System.out.println("User.num = " + User.num);
    }

    /**
     * 未进行 clinit 初始化
     */
    @Test
    public void callStaticFinalFieldInClass() {
        System.out.println("User.num1 = " + User.num1);
        System.out.println("Order.str = " + Order.str);
    }

    @Test
    public void callInterfaceField() {
//        System.out.println("CompareA.NUM = " + CompareA.NUM);
        System.out.println("CompareA.NUM2 = " + CompareA.NUM2);
    }
}

class User {
    public static final int num1 = 20;
    public static int num = 1;

    static {
        System.out.println("User class initialization processing...");
    }
}