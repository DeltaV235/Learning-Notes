package com.wuyue.thread;

public class ThreadLocalTest {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>() {
        @Override
        protected Integer initialValue() {
            return (int)(Math.random()*500);
        }
    };

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "\t" + threadLocal.get());
        new Thread(()-> System.out.println(Thread.currentThread().getName() + "\t" + threadLocal.get())).start();
        new Thread(()-> System.out.println(Thread.currentThread().getName() + "\t" + threadLocal.get())).start();
        new Thread(()-> System.out.println(Thread.currentThread().getName() + "\t" + threadLocal.get())).start();
    }
}
