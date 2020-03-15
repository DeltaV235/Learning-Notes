package com.wuyue;

import java.lang.reflect.Proxy;

/**
 * @author DeltaV235
 * @version 1.0
 * @className CalcuProxy
 * @description
 * @date 2020/3/15 20:57
 */
public class CalcuProxy {
    public static Object getProxy(Object target) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    return method.invoke(target, args);
                });
    }
}















