package com.deltav;


/**
 * test location of static variable. Refer to <b>In-depth understanding of the java virtual machine</b>.
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/6/13 15:09
 */
public class StaticObjTest {
    public static void main(String[] args) {
        Test test = new Test();
        test.foo();
    }

    static class Test {
        static ObjectHolder staticObj = new ObjectHolder();
        ObjectHolder instanceObj = new ObjectHolder();

        void foo() {
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("done");
        }
    }

    private static class ObjectHolder {
    }
}
