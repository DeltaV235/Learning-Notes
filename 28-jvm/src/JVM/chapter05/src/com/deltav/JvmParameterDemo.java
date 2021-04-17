package com.deltav;

import java.util.concurrent.TimeUnit;

/**
 * @author DeltaV235
 * @version 1.0
 * @date 2021/4/17 16:09
 */
public class JvmParameterDemo {
    public static void main(String[] args) {
        System.out.println("main thread running");

        long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;

        System.out.println("initialMemory = " + initialMemory + " MiB ");
        System.out.println("maxMemory = " + maxMemory + " MiB ");

        System.out.println("OS Memory = " + initialMemory * 64.0 / 1024 + " GiB ");
        System.out.println("OS Memory = " + maxMemory * 4.0 / 1024 + " GiB ");

        try {
            TimeUnit.SECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
