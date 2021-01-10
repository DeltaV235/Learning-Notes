package com.deltav;

import java.util.concurrent.TimeUnit;

/**
 * 反编译、字节码测试
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2020/11/29 16:47
 */
public class DecompileDemo {
    public static void main(String[] args) {
        int i = 2;
        int j = 3;
        int sum = i + j;

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Hello World!");
    }
}
