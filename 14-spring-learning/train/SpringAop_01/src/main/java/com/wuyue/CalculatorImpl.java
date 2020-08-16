package com.wuyue;

import org.springframework.stereotype.Service;

/**
 * @author DeltaV235
 * @version 1.0
 * @className CalculatorImpl
 * @description
 * @date 2020/3/14 17:34
 */
@Service
public class CalculatorImpl/* implements calculator*/ {
    public int add(int a, int b) {
        System.out.println("目标方法执行");
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }

    public int mul(int a, int b) {
        return a * b;
    }

    public int div(int a, int b) {
        return a / b;
    }
}














