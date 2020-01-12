package com.wuyue.thread.homework.print;

public class NumberPrinter extends Thread {
    private Printer p;

    public NumberPrinter(Printer p) {
        this.p = p;
    }

    @Override
    public void run() {
        int i = 1;
        while (i <= 52) {
            try {
                p.print(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}
