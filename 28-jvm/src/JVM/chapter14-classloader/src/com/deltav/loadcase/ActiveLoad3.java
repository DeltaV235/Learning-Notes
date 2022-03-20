package com.deltav.loadcase;

import org.junit.Test;

import java.util.Random;

interface CompareB extends CompareC {
    Object obj = new Object() {
        {
            System.out.println("CompareB interface initialization...");
        }
    };

    int NUM = new Random().nextInt(10);

    default void method() {
        System.out.println("default method");
    }
}

interface CompareC {
    Object obj = new Object() {
        {
            System.out.println("CompareC interface initialization...");
        }
    };
    int NUM = 1;
}

/**
 * 4. 当使用java.lang.reflect包中的方法反射类的方法时。比如：`Class.forname("java.lang.Object")`
 * <p>
 * 5. 当初始化子类时，如果发现其分类还没有进行过初始化，则需要先触发其父类的初始化
 * <ul>当Java虚拟机初始化一个类时，要求它的所有父类都已经被初始化，但是这条规则并不适用于接口</ul>
 * <ul>在初始化一个类时，并不会先初始化它所实现的接口</ul>
 * <ul>在初始化一个接口时，并不会先初始化它的父接口</ul>
 * <ul>因此，一个父接口并不会因为它的子接口或者实现类的初始化而初始化，只有当程序首次使用特定接口的静态字段时，才会导致该接口的初始化</ul>
 * <p>
 * 6. 如果一个接口定义了default方法，那么直接实现或者间接实现该接口的类的初始化，该接口要在其之前被初始化
 *
 * @author DeltaV235
 * @version 1.0
 */
public class ActiveLoad3 {

    @Test
    public void reflect() throws ClassNotFoundException {
        Class<?> aClass = Class.forName("com.deltav.loadcase.Order");
    }

    @Test
    public void test1() {
        new Son();
        System.out.println("CompareB.NUM = " + CompareB.NUM);
    }

    @Test
    public void test2() {
        new Son();
    }

}

class Parent {
    static {
        System.out.println("parent class initialization...");
    }
}

class Son extends Parent implements CompareB {
    static {
        System.out.println("son class initialization...");
    }
}
