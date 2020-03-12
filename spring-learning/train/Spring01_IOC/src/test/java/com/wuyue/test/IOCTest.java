package com.wuyue.test;

import com.wuyue.domain.Car;
import com.wuyue.domain.Person;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author DeltaV235
 * @version 1.0
 * @className IOCTest
 * @description
 * @date 2020/3/11 15:34
 */
public class IOCTest {

    private ApplicationContext ioc;

    @Before
    public void init() {
        ioc = new ClassPathXmlApplicationContext("ioc.xml");
    }

    @Test
    public void test() {
        Person person01 = (Person) ioc.getBean("person01");
        System.out.println(person01);
    }

    /**
     * 根据bean的类型从IOC容器中获取bean的实例★
     */
    @Test
    public void test02() {
//        Person person01 = ioc.getBean(Person.class);
//        System.out.println(person01);
        Person person02 = ioc.getBean("person02", Person.class);
        System.out.println(person02);
    }

    @Test
    public void test03() {
//        Person person03 = ioc.getBean("person03", Person.class);
//        System.out.println(person03);
//        Person person04 = ioc.getBean("person04", Person.class);
//        System.out.println(person04);
        Person person05 = ioc.getBean("person05", Person.class);
        System.out.println(person05);
    }

    /**
     * @author DeltaV235
     * @date 2020/3/11 18:21
     * @description 测试自定义引用类型的赋值，外部引用
     */
    @Test
    public void test04() {
        Person personWithCar = ioc.getBean("personWithCar", Person.class);
        System.out.println(personWithCar);
        System.out.println(personWithCar.getCar());

        Car car01 = ioc.getBean("car01", Car.class);
        car01.setCarName("hehe");
        System.out.println(personWithCar);
        System.out.println(personWithCar.getCar());
    }

    /**
     * @author DeltaV235
     * @date 2020/3/11 18:28
     * @description 测试自定义对象的内部引用
     */
    @Test
    public void test05() {
        Person personWithInnerCar = ioc.getBean("personWithInnerCar", Person.class);
        System.out.println(personWithInnerCar);
        System.out.println(personWithInnerCar.getCar());
    }

    /**
     * @author DeltaV235
     * @date 2020/3/11 19:42
     * @description 测试List对象的赋值
     */
    @Test
    public void test06() {
        Person personWithList = ioc.getBean("personWithList", Person.class);
        System.out.println(personWithList);
    }

    /**
     * @author DeltaV235
     * @date 2020/3/11 19:42
     * @description 测试Map
     */
    @Test
    public void test07() {
        Person personWithMap = ioc.getBean("personWithMap", Person.class);
        System.out.println(personWithMap);
    }

    /**
     * @author DeltaV235
     * @date 2020/3/11 20:50
     * @description 测试bean的继承是否是属性的克隆
     */
    @Test
    public void test08() {
        Person child = ioc.getBean("child", Person.class);
        System.out.println(child);
        child.getCar().setCarName("hehe");

        Person parent = ioc.getBean("parent", Person.class);
        System.out.println(parent);
        System.out.println(child);
    }
}














