package com.wuyue;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author DeltaV235
 * @version 1.0
 * @className CalculatorProxy
 * @description
 * @date 2020/3/14 17:40
 */
public class CalculatorProxy {
    public static Calculator getProxy(Calculator calculator) {
        return (Calculator) Proxy.newProxyInstance(calculator.getClass().getClassLoader(), new Class[]{Calculator.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        try {
                            LogUtils.methodStart(method, args);
                            Object result = method.invoke(calculator, args);
                            LogUtils.methodEnd(method, result);
                            return result;
                        } catch (Exception e) {
                            LogUtils.methodException(method, e);
                        }
                        return null;
                    }
                });
    }
}














