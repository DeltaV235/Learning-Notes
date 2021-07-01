package com.deltav;

import java.util.concurrent.TimeUnit;

/**
 * Show JIT compiler work time by using jvisualvm or jconsole
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/7/2 0:17
 */
public class JITTest {
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            System.out.println(i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
