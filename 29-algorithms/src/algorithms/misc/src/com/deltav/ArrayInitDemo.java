package com.deltav;

/**
 * 数组初始化测试
 * <p>
 * 数组元素在没赋值前，对于 Java 原生类型，byte char short int long float double 值为 0 或 ''
 * boolean 为 false
 * <p>
 * 引用类型为 null
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2020/11/23 22:51
 */
public class ArrayInitDemo {
    public static void main(String[] args) {
        boolean[] array = new boolean[5];
        for (int i = 1; i < array.length - 1; i++) {
            array[i] = true;
        }

        for (boolean element : array) {
            System.out.println("element = " + element);
        }
    }
}
