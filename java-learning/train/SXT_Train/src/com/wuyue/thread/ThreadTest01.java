package com.wuyue.thread;

public class ThreadTest01 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 200; i++)
            System.out.println("otherThread\t" + i);
    }

    public static void main(String[] args) {
        ThreadTest01 tt = new ThreadTest01();
        tt.start();
        for (int i = 0; i < 200; i++)
            System.out.println("mainThread\t" + i);
    }
}
