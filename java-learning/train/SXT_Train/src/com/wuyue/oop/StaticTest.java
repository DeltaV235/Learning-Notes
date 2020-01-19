package com.wuyue.oop;

public class StaticTest {
    public static void main(String[] args) {
        Ha ha = null;   //创建引用类型变量不会初始化对应的类
        ha = new Ha();
    }
}

class Ha implements Cloneable{
    private static int age;
    private static String name;

    static {
        age = 100;
        name = "Elder";
        System.out.println("name:" + name + " age:" + age);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
