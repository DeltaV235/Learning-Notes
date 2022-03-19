package com.deltav;

/**
 * 类初始化测试
 *
 * @author DeltaV235
 * @version 1.0
 */
public class InitializationTest {
    public static int id = 1;
    public static int number;

    static {
        number = 2;
        System.out.println("hello parent!");
    }
}
