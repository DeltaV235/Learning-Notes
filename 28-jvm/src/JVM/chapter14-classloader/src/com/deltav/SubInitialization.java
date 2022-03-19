package com.deltav;

/**
 * @author DeltaV235
 * @version 1.0
 */
public class SubInitialization extends InitializationTest {
    static {
        number = 4;
        System.out.println("Hello Son");
    }

    public static void main(String[] args) {
        System.out.println(number);
    }
}
