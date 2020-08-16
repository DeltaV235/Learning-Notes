package com.wuyue.test;

import com.wuyue.domain.Person;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author DeltaV235
 * @version 1.0
 * @className IOCTest
 * @description
 * @date 2020/3/11 15:34
 */
public class IOCTest2 {

    private ApplicationContext ioc;

    @Before
    public void init() {
        ioc = new ClassPathXmlApplicationContext("ioc2.xml");
    }

    @Test
    public void test() {
        Person bean = (Person) ioc.getBean("person02");
        System.out.println(bean);
    }

    @Test
    public void test02() {
        Object person01 = ioc.getBean("person02");
        Object person02 = ioc.getBean("person02");
        System.out.println(person02 == person01);
    }

    @Test
    public void test03() {
        Object carStaticFactory = ioc.getBean("carStaticFactory");
        System.out.println(carStaticFactory);
//        Object car = ioc.getBean("car");
//        System.out.println(car);
        Object car2 = ioc.getBean("car2");
        Object car3 = ioc.getBean("car2");
        System.out.println(car3 == car2);
    }

    @Test
    public void test04(){
        ConfigurableApplicationContext ioc = (ConfigurableApplicationContext) this.ioc;
        ioc.close();
    }

}














