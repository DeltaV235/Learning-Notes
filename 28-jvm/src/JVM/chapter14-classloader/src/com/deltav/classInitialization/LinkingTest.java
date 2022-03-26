package com.deltav.classInitialization;

/**
 * 类加载阶段 2：链接阶段
 * <p>
 * 基本数据类型：非 final 修饰的变量，在准备环节进行默认初始化赋值（0 or false or null）。
 * final 修饰后，在准备环节直接进行显示赋值。
 * <p>
 * 如果使用字面量的方式定义一个字符串的常量的话，也在准备阶段直接进行显示赋值。
 *
 * @author DeltaV235
 * @version 1.0
 */
public class LinkingTest {
    private static final int num = 1;
    private static final String constStr = "CONST";
    private static final String constStr1 = new String("CONST");
    private static long id;
}
