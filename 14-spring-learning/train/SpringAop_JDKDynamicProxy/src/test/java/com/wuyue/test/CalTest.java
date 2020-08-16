package com.wuyue.test;

import com.wuyue.CalcuProxy;
import com.wuyue.Calculator;
import com.wuyue.CalculatorImpl;
import com.wuyue.CalculatorProxy;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @author DeltaV235
 * @version 1.0
 * @className CalTest
 * @description
 * @date 2020/3/14 17:36
 */
public class CalTest {
    @Test
    public void testCal() {
        Calculator calculator = CalculatorProxy.getProxy(new CalculatorImpl());
        calculator.add(2, 4);
        calculator.mul(2, 4);
        calculator.div(2, 0);
    }

    @Test
    public void testProxy() {
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
//        System.out.println(Proxy.getProxyClass(Calculator.class.getClassLoader(), Calculator.class));
        Calculator calculator = (Calculator) CalcuProxy.getProxy(new CalculatorImpl());
        System.out.println(calculator.add(1, 3));
    }

    public static void main(String[] args) {
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        System.out.println(Proxy.getProxyClass(Calculator.class.getClassLoader(), Calculator.class));
        Calculator calculator = (Calculator) CalcuProxy.getProxy(new CalculatorImpl());
        System.out.println(calculator.add(1, 3));
    }
}














