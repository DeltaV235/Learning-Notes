package com.deltav;

/**
 * Test System.gc() and Runtime.getRuntime().gc()
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/9/5 15:55
 */
public class SystemGCTest {
    public static void main(String[] args) {
        new SystemGCTest();
//        System.gc();
//        Runtime.getRuntime().gc();

        System.runFinalization();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize() has been called");
    }
}
