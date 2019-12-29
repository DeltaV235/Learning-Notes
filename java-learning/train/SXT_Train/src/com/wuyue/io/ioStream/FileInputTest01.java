package com.wuyue.io.ioStream;

import java.io.*;

/**
 * 测试字节流
 *
 * @author DeltaV235
 */
public class FileInputTest01 {
    public static void main(String[] args) {
        File src = new File("LICENSE.test");
        try {
            InputStream inputStream = new FileInputStream(src);
            char data1 = (char)inputStream.read();
            char data2 = (char)inputStream.read();
            System.out.println(data1);
            System.out.println(data2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
