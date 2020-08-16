package com.wuyue.thread.homework.print;

public class LetterPrinter extends Thread {
    private Printer p;

    public LetterPrinter(Printer p) {
        this.p = p;
    }

    @Override
    public void run() {
        char c = 'A';
        while (c <= 'Z') {
            try {
                p.print(c);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            c++;
        }
    }
}
