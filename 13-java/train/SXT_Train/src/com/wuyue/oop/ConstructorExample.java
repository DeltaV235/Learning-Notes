package com.wuyue.oop;

class ExtendTest {
    private String name;
    public ExtendTest() {
        name = "wuyue";
    }
}


class SonNode extends ExtendTest {
    private int id;

    public SonNode() {
        this.id = 90;
    }
}

class Foo {
    int i = 1;

    Foo() {
        System.out.println(i);
        int x = getValue();
        System.out.println(x);
    }

    {
        i = 2;
    }

    protected int getValue() {
        return i;
    }
}

//子类
class Bar extends Foo {
    int j ;

    Bar() {
        j = 2;
    }

    Bar(int i) {
        this();
    }

    {
        System.out.println(getValue());
    }

    @Override
    protected int getValue() {
        return j;
    }
}

public class ConstructorExample {
    public static void main(String... args) {
        Bar bar = new Bar(3);
        System.out.println(bar.getValue());
    }
}
