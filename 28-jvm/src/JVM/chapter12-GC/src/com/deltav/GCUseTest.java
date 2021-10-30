package com.deltav;

import java.util.concurrent.TimeUnit;

/**
 * @author DeltaV235
 * @version 1.0
 * <p>
 * -XX:+PrintCommandLineFlags
 * -XX:+UseSerialGC
 */
public class GCUseTest {
    public static void main(String[] args) {
        try {
            TimeUnit.MINUTES.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
