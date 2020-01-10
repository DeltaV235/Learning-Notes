package com.wuyue.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
    public static void main(String[] args) {
        Timer timer = new Timer();
//        timer.schedule(new MyTimer(), 2000, 5000);
        timer.schedule(new MyTimer(), new Date(System.currentTimeMillis() + 5000), 2000);
    }
}

class MyTimer extends TimerTask {
    @Override
    public void run() {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(new Date()) + "\tHello world!");
    }
}
