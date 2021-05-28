package com.deltav;

import java.util.concurrent.TimeUnit;

/**
 * Demo code of Method Area class count
 * <p>
 * JVM Parameters:
 * before JDK7
 * -XX:PermSize=100m -XX:MaxPermSize=100m
 * <p>
 * JDK8 and after
 * -XX:MetaspaceSize=100m -XX:MaxMetaspaceSize=100m
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/5/9 20:06
 */
public class MethodAreaDemo {
    public static void main(String[] args) {
        try {
            TimeUnit.MINUTES.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
