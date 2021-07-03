package com.deltav;

import java.util.concurrent.locks.LockSupport;

/**
 * Test park method with blocker parameter.
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/7/3 18:19
 */
public class BlockerTest {
    public static void main(String[] args) {
        BlockerTest blockerTest = new BlockerTest();
        blockerTest.testPark();
    }

    private void testPark() {
        LockSupport.park(this);
        System.out.println("begin park!");
//        LockSupport.parkNanos(1000000000);
        System.out.println("end park!");
    }
}
