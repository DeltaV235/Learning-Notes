package com.wuyue.io.buffer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferWriterTest {
    public static void main(String[] args) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Test/CopyTest/bufferedWriter.test"))) {
            bw.write("不要打我啊");
            bw.newLine();
            bw.write("茜茜铪蛤");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
