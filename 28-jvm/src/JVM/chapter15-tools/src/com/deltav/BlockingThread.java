package com.deltav;

import java.util.concurrent.TimeUnit;

/**
 * hide in jps
 * -XX:+UsePerfData
 *
 * @author DeltaV235
 * @version 1.0
 */
public class BlockingThread {
    public static void main(String[] args) {
        try {
            TimeUnit.DAYS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
