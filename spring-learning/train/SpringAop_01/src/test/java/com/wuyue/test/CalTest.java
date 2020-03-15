package com.wuyue.test;

import com.wuyue.Calculator;
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

    private ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void testCal() {
        Calculator bean = ioc.getBean(Calculator.class);
        bean.add(1, 2);
        bean.div(3, 4);
        System.out.println(bean);
        System.out.println(bean.getClass());
    }

    @Test
    public void testObject() {
        class TestInnerClass {
        }
        TestInnerClass testClass = new TestInnerClass();
        System.out.println(testClass);
        System.out.println(testClass.getClass());
    }
}














