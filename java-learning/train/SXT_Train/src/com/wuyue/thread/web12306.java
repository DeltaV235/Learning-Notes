package com.wuyue.thread;

public class web12306 implements Runnable{
    private int ticket = 10;

    @Override
    public void run() {
        while (true) {
            if (ticket <= 0)
                break;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + ticket--);
        }
    }

    public static void main(String[] args) {
        web12306 web = new web12306();
        new Thread(web,"WY").start();
        new Thread(web,"KWY").start();
        new Thread(web,"LYH").start();
    }
}
