package com.deltav.instruction;

import org.junit.Test;

/**
 * 类型转换指令
 *
 * @author DeltaV235
 * @version 1.0
 */
public class ClassCastTest {

    public void upCast1() {
        int i = 10;

        long l = i;
        float f = i;
        double d = i;

        float f1 = l;
        double d1 = l;

        double d2 = f1;
    }

    /**
     * 宽化类型转换的精度损失问题
     * <p>
     * f = 1.2312312E8
     * d = 1.2312312312312312E17
     * <p>
     * 经过类型转换后，个位的精度发生的损失
     */
    @Test
    public void upCast2() {
        int i = 123123123;
        float f = i;
        System.out.println("f = " + f);

        long l = 123123123123L;
        l = 123123123123123123L;

        double d = l;
        System.out.println("d = " + d);
    }

    /**
     * 对于 byte short char 类型的宽化类型转换时，将此类型看做 int 类型处理。
     *
     * @param b byte
     */
    public void upCast3(byte b) {
        int i = b;
        long l = b;
        double d = b;
    }

    public void upCast4(short b) {
        int i = b;
        long l = b;
        double d = b;
    }

    /**
     * 窄化类型转换
     */
    public void downCast1() {
        int i = 10;
        byte b = (byte) i;
        short s = (short) i;
        char c = (char) i;

        long l = 10L;
        int i1 = (int) l;
        byte b1 = (byte) l;
    }

    public void downCast2() {
        float f = 10;
        long l = (long) f;
        int i = (int) f;
        byte b = (byte) f;

        double d = 10;
        byte b1 = (byte) d;
    }

    /**
     * 0 bipush 10
     * 2 istore_1
     * 3 iload_1
     * 4 <b>i2b</b>
     * 5 istore_2
     * 6 return
     */
    public void downCast3() {
        short s = 10;
        byte b = (byte) s;
    }

    /**
     * 窄化类型转换的数据精度损失
     */
    @Test
    public void downCast4() {
        int i = 128;
        byte b = (byte) i;
        System.out.println("b = " + b);
    }

    /**
     * 测试浮点数 NaN 以及无穷大的情况
     */
    @Test
    public void downCast5() {
        double d1 = Double.NaN;
        int i = (int) d1;
        System.out.println("i = " + i);

        double d2 = Double.POSITIVE_INFINITY;
        long l = (long) d2;
        int j = (int) d2;
        System.out.println("l = " + l);
        System.out.println("j = " + j);

        // Nan
        float f1 = (float) d1;
        System.out.println("f1 = " + f1);

        // Infinite
        float f2 = (float) d2;
        System.out.println("f2 = " + f2);
    }

}
