package com.deltav;

import java.util.concurrent.locks.LockSupport;

/**
 * To test unpark method in LockSupport Class.
 * In this case, unpark will assign a licence to caller thread, then park method will consume the licence and return
 * immediately.
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/7/3 17:19
 */
public class UnparkTest {
    public static void main(String[] args) {
        System.out.println("begin park!");

        LockSupport.unpark(Thread.currentThread());

        LockSupport.park();

        System.out.println("end park!");
    }
}
