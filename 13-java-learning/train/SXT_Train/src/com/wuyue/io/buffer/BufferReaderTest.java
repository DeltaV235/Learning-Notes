package com.wuyue.io.buffer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferReaderTest {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("Test/CopyTest/copy.test"));) {
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
