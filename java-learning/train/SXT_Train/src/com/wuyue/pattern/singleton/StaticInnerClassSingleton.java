package com.wuyue.pattern.singleton;

/**
 * 静态内部类单例模式
 * 线程安全，调用效率高，支持延时加载
 */
public class StaticInnerClassSingleton {
    // 类初始化时能够保证线程安全
    private static class SingletonInnerClass {
        private static final StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
    }

    private StaticInnerClassSingleton() {
    }

    public static StaticInnerClassSingleton getInstance() {
        return SingletonInnerClass.instance;
    }
}
