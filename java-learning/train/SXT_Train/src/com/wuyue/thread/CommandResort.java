package com.wuyue.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommandResort {
    private static int a;
    private static boolean flag;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("t1 start time : " + new SimpleDateFormat("mm:ss").format(new Date()));
            a = 1;
            flag = true;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            System.out.println("t2 start time : " + new SimpleDateFormat("mm:ss").format(new Date()));
            if (flag)
                a *= 1;
            if (a == 0)
                System.out.println("happen before a->" + a);
        });

        t1.start();

        t1.join();
        t2.start();
        t2.join();
    }
}
