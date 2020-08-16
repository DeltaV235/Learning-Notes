package com.wuyue.test;

import com.wuyue.CalculatorImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author DeltaV235
 * @version 1.0
 * @className CalTest
 * @description
 * @date 2020/3/14 17:36
 */
public class CalTest {

    private ApplicationContext ioc;

    @Before
    public void init() {
        ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void testCal() {
        CalculatorImpl bean = ioc.getBean(CalculatorImpl.class);
        Object calculatorImpl = ioc.getBean("calculatorImpl");
        System.out.println(bean.add(1, 2));
        System.out.println("-".repeat(40));
    }
}














