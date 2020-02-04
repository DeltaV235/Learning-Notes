package com.wuyue.jdbc;

public class test {
    public static void main(String[] args) {
        Father father = new Son();
        father.p();
    }
}

class Father {
    public void p() {
        System.out.println("father");
    }
}

class Son extends Father {
    @Override
    public void p() {
        System.out.println("son");
    }
}
