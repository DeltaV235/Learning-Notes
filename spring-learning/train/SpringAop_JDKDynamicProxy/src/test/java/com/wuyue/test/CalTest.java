package com.wuyue.test;

import com.wuyue.Calculator;
import com.wuyue.CalculatorImpl;
import com.wuyue.CalculatorProxy;
import org.junit.Test;

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
}














