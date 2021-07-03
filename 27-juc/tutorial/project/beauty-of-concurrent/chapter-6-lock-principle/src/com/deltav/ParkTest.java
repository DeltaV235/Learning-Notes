package com.deltav;

import java.util.concurrent.locks.LockSupport;

/**
 * Class for testing park method in LockSupport.
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/7/3 17:17
 */
public class ParkTest {
    public static void main(String[] args) {
        System.out.println("begin park!");

        LockSupport.park();

        System.out.println("end park!");
    }
}
