package com.deltav.instruction;

/**
 * 条件跳转指令
 *
 * @author DeltaV235
 * @version 1.0
 */
public class IfSwitchGotoTest {
    /**
     * 1.条件跳转指令
     */
    public void compare1() {
        int a = 0;
        if (a == 0) {
            a = 10;
        } else {
            a = 20;
        }
    }

    public boolean compareNull(String str) {
        if (str == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 结合比较指令
     */
    public void compare2() {
        float f1 = 9;
        float f2 = 10;
        System.out.println(f1 < f2);
    }

    public void compare3() {
        int i1 = 10;
        long l1 = 20;
        System.out.println(i1 < l1);
    }

    public int compare4(double d) {
        if (d > 50.0) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * 2.比较条件跳转指令
     */
    public void ifCompare1() {
        int i = 10;
        int j = 20;
        System.out.println(i > j);
    }

    public void ifCompare2() {
        short s1 = 9;
        byte b1 = 10;
        System.out.println(s1 > b1);
    }

    public void ifCompare3() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        System.out.println(obj1 == obj2);
        System.out.println(obj1 != obj2);
    }

    public void ifCompare4() {
        boolean b1 = true;
        boolean b2 = false;
        if (b1 == b2) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }

    /**
     * 3.多条件分支跳转
     */
}
