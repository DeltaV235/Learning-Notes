package com.wuyue;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author DeltaV235
 * @version 1.0
 * @className NotSafeMapDemo
 * @description 线程不安全 Map Demo
 * @date 2020/10/8 15:03
 */
public class NotSafeMapDemo {
    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();
//        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 200; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
