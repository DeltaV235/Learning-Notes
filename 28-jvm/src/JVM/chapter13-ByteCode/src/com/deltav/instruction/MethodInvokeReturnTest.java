package com.deltav.instruction;

import java.util.Date;

interface Inter {
    static void method2() {
    }

    default void method1() {
    }
}

/**
 * 方法调用与返回指令
 *
 * @author DeltaV235
 * @version 1.0
 */
public class MethodInvokeReturnTest {

    public static void methodStatic() {
    }

    /**
     * 方法调用指令：invokespecial: 静态分派
     */
    public void invoke1() {
        Date date = new Date();

        Thread thread = new Thread();

        super.toString();

        methodPrivate();
    }

    /**
     * 方法调用指令：invokestatic: 静态分派
     */
    public void invoke2() {
        methodStatic();
    }

    /**
     * 方法调用指令：invokeinterface
     */
    public void invoke3() {
        Thread t1 = new Thread();
        ((Runnable) t1).run();

        Comparable<Integer> comparable = null;
        comparable.compareTo(111);
    }

    /**
     * 方法调用指令：invokevirtual: 动态分派
     */
    public void invoke4() {
        Thread t1 = null;
        t1.run();
    }

    /**
     * Interface 中 default & static 方法的调用
     */
    public void invoke5() {
        Inter inter = new Impl();

        inter.method1();

        Inter.method2();
    }

    /**
     * 方法的返回指令
     *
     * @return
     */
    public int returnInt() {
        int i = 500;
        return i;
    }

    public double returnDouble() {
        return 0.1;
    }

    public String returnString() {
        return "hello, world";
    }

    public int[] returnArr() {
        return null;
    }

    public float returnFloat() {
        int i = 10;
        return i;
    }

    public byte returnByte() {
        return 127;
    }

    public void returnVoid() {
    }

    private void methodPrivate() {
    }
}

class Impl implements Inter {
}
