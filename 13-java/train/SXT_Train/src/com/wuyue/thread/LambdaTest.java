package com.wuyue.thread;

public class LambdaTest {
    public static void main(String[] args) {
        Object[] object = new Object[4];
        Test go = (String s, Object[] o) -> {
            System.out.println(s + o);
            return 255;
        };
        System.out.println(go.lambda("go for launch", object));
    }
}

interface Test {
    int lambda(String name, Object[] objects);
}

