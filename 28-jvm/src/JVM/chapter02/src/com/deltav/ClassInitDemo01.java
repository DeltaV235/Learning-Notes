package com.deltav;

/**
 * 测试 <Clinit> 类初始化方法，类变量的值由赋值的顺序决定。
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/2/11 22:21
 */
public class ClassInitDemo01 {
    private static int number = 10;
    private static int magicNum = 50;

    static {
        number = 20;
        magicNum = 30;
    }

    public static void main(String[] args) {
        System.out.println("number = " + number);
        System.out.println("magicNum = " + magicNum);
    }
}
