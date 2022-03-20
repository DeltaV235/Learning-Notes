package com.deltav.loadcase;

import org.junit.Test;

/**
 * 1. 当访问一个静态字段时，只有真正声明这个字段的类才会被初始化
 * <ul>当通过子类引用父类的静态变量，不会导致子类初始化</ul>
 * 2. 通过数组定义类引用，不会触发此类的初始化
 * 3. 引用变量不会触发此类或接口的初始化。因为常量在链接阶段就已经被显式赋值了
 * 4. 调用 `ClassLoader` 类的 `loadClass()` 方法加载一个类，并不是对类的主动使调用 `ClassLoader` 类的 `loadClass()` 方法加载一个类，并不是对类的主动使
 *
 * @author DeltaV235
 * @version 1.0
 */
public class PassiveLoad {
    @Test
    public void test1() {
        System.out.println("Slave.NUM = " + Slave.NUM);
    }

    @Test
    public void test2() {
        Master[] masters = new Master[20];
    }

    @Test
    public void test3() {
        System.out.println("Master.str = " + Master.str);
    }

    @Test
    public void test4() throws ClassNotFoundException {
        ClassLoader.getSystemClassLoader().loadClass("com.deltav.loadcase.Master");
    }

}

class Master {
    public static final String str = "test";
    public static int NUM = 2;

    static {
        System.out.println("Master class initialization start...");
    }
}

class Slave extends Master {
    static {
        System.out.println("Slave class initialization start...");
    }
}
