package com.wuyue.oop;

/**
 * 测试非静态内部类
 *
 * @author DeltaV235
 */
public class InnerClassTest {
    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner2 = outer.new Inner();
        inner2.show();
        Outer.Inner inner = new Outer().new Inner();
        inner.show();
    }
}

class Outer {
    private int age = 10;
    public void testOuter() {
        System.out.println("test");
    }

    class Inner {
        public void show() {
            System.out.println(Outer.this.age);
        }
    }
}
