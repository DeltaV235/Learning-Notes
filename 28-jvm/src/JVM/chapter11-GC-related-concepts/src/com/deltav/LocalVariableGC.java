package com.deltav;

/**
 * @author DeltaV235
 * @version 1.0
 * @date 2021/9/5 19:54
 */
public class LocalVariableGC {
    public static void main(String[] args) {
        LocalVariableGC localVariableGC = new LocalVariableGC();
        localVariableGC.localVariableGC5();
    }

    public void localVariableGC1() {
        byte[] bytes = new byte[10 * 1024 * 1024];
        System.gc();
    }

    public void localVariableGC2() {
        byte[] bytes = new byte[10 * 1024 * 1024];
        bytes = null;
        System.gc();
    }

    public void localVariableGC3() {
        {
            byte[] bytes = new byte[10 * 1024 * 1024];
        }
        System.gc();
    }

    public void localVariableGC4() {
        {
            byte[] bytes = new byte[10 * 1024 * 1024];
        }
        int value = 10;
        System.gc();
    }

    public void localVariableGC5() {
        localVariableGC1();
        System.gc();
    }
}
