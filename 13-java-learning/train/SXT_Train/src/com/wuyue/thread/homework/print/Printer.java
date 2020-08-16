package com.wuyue.thread.homework.print;

public class Printer {
    private int index = 1;

    public synchronized void print(int i) throws InterruptedException {
        while (index % 3 == 0)
            wait();
        System.out.print(i);
        notifyAll();
        index++;
    }

    public synchronized void print(char c) throws InterruptedException {
        while (index % 3 != 0)
            wait();
        System.out.print(c);
        notifyAll();
        index++;
    }
}
