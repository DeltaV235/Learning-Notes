package com.wuyue.io.printStream;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintStreamTest {
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream ps = System.out;
        ps.println("test");

        System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("")),true));
    }
}
