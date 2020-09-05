package com.wuyue.thread.homework.print;

public class Test {
    public static void main(String[] args) {
        Printer printer = new Printer();
        new NumberPrinter(printer).start();
        new LetterPrinter(printer).start();
    }
}
