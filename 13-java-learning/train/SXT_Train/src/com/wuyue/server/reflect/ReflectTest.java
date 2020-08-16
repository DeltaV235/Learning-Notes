package com.wuyue.server.reflect;

import java.lang.reflect.InvocationTargetException;

public class ReflectTest {
    public static void main(String[] args) throws ReflectiveOperationException {
        Class clz = Phone.class;
        clz = new Phone().getClass();
        clz = Class.forName("com.wuyue.server.reflect.Phone");
        Phone phone = (Phone) clz.getConstructor().newInstance();
        System.out.println(phone);
    }
}

class Phone {
    public Phone() {
    }
}
