package com.wuyue.thread;

public class VolatileTest {
    private static volatile int num = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (num == 0) {
            }
        }).start();
        Thread.sleep(2000);
        num = 1;
    }
}
