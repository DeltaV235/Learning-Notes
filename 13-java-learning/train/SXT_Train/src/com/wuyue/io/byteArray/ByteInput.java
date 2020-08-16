package com.wuyue.io.byteArray;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteInput {
    public static void main(String[] args) {
        byte[] bytes = "Show me the code".getBytes();
        InputStream is = null;
        try {
            is = new ByteArrayInputStream(bytes);
            int length = -1;
            byte[] flush = new byte[1024];
            while ((length = is.read(flush)) != -1) {
                String temp = new String(flush, 0, length);
                System.out.print(temp);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
