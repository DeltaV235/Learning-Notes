package com.wuyue;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author DeltaV235
 * @version 1.0
 * @className NotSafeSet
 * @description 线程不安全 set Demo
 * @date 2020/10/8 12:38
 */
public class NotSafeSetDemo {
    public static void main(String[] args) {
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 200; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
