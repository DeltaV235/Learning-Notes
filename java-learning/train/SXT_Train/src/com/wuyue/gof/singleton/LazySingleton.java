package com.wuyue.gof.singleton;

/**
 * 懒汉式单例
 */
public class LazySingleton {
    // 类初始化时不实例化该对象，在使用的时候再实例化。
    private static LazySingleton instance;

    private LazySingleton() {
    }

    // 同步方法，调用效率低。
    public static synchronized LazySingleton getInstance() {
        if (instance == null)
            instance = new LazySingleton();
        return instance;
    }
}
