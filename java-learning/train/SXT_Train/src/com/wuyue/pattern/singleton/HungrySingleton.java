package com.wuyue.pattern.singleton;

/**
 * 饿汉式单例模式
 */
public class HungrySingleton {
    // 类初始化时，立刻实例化该对象，在类初始化时线程安全，所以getInstance()方法不用添加同步。
    private final static HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return instance;
    }
}
