package com.wuyue.pattern.singleton;

/**
 * 枚举式单例模式
 * 不能延时加载
 */
public enum EnumSingleton {
    // 枚举元素本身就是单例对象
    INSTANCE;

    // 添加需要的操作
    public void singletonOperation() {
    }
}
