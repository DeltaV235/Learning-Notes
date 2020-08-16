package com.wuyue.io.byteArray;

import java.io.ByteArrayOutputStream;

public class ByteOutput {
    public static void main(String[] args) {
        byte[] flush = "Show me the code".getBytes();
        ByteArrayOutputStream byteArrayOutputStream = null;
        byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(flush, 0, flush.length);
        System.out.println(byteArrayOutputStream.toByteArray().length);
        System.out.println(new String(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size()));
    }
}
