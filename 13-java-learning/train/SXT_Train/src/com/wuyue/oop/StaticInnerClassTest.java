package com.wuyue.oop;

public class StaticInnerClassTest {
    public static void main(String[] args) {
        Outer2.Inner.show();
    }
}

class Outer2 {
    private static int age = 3;

    static class Inner {
        static void show() {
            System.out.println(Outer2.age);
        }
    }
}
