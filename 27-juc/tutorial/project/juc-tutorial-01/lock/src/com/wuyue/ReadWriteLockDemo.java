package com.wuyue;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock 示例类
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2020/11/8 20:58
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        Cache cache = new Cache();
        // write threads
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                cache.put("key" + temp, temp);
            }).start();
        }

        // read threads
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                Object value = cache.get("key" + temp);
                System.out.println("value = " + value);
            }).start();
        }
    }

    static class Cache {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Lock lock = new ReentrantLock();
        private Map<String, Object> map = new HashMap<>();

        public void put(String key, Object element) {
            readWriteLock.writeLock().lock();
//            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\twrite start\t" + element);
                map.put(key, element);
                System.out.println(Thread.currentThread().getName() + "\twrite finish\t" + element);
            } finally {
                readWriteLock.writeLock().unlock();
//                lock.unlock();
            }

        }

        public Object get(String key) {
            readWriteLock.readLock().lock();
//            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\tread start\t" + key);
                Object retVal = map.get(key);
                System.out.println(Thread.currentThread().getName() + "\tread finish\t" + key);
                return retVal;
            } finally {
                readWriteLock.readLock().unlock();
//                lock.unlock();
            }

        }
    }
}
