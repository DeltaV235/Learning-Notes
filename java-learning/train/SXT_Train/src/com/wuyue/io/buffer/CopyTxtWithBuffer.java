package com.wuyue.io.buffer;

import java.io.*;

public class CopyTxtWithBuffer {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("Test/CopyTest/copy.test"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("Test/CopyTest/copy-copy.test"))) {
            String temp = null;
            while ((temp = br.readLine()) != null){
                bw.write(temp);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
