package com.wuyue.thread;

public class SafeWeb12306 implements Runnable {
    private int ticket = 100;

    @Override
    public void run() {
        while (true) {
            if (!buy())
                break;
        }
    }

    public synchronized boolean buy() {
        if (ticket <= 0)
            return false;
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " " + ticket--);
        return true;
    }

    public static void main(String[] args) {
        SafeWeb12306 web = new SafeWeb12306();
        new Thread(web, "WY").start();
        new Thread(web, "KWY").start();
        new Thread(web, "LYH").start();
    }
}
