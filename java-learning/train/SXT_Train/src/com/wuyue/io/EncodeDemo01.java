package com.wuyue.io;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class EncodeDemo01 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String test = "abc中蛤，";
        byte[] bytes = test.getBytes();
        for (byte b:bytes)
            System.out.println(b);
        System.out.println("-----------------------------------------------------");
        byte[] b2 = test.getBytes("UTF_16LE");
        for (byte b:b2)
            System.out.println(b);
        System.out.println("-----------------------------------------------------");
        byte[] b3 = test.getBytes("GBK");
        for (byte b:b3)
            System.out.println(b);
        System.out.println(new String(bytes,0, bytes.length, StandardCharsets.UTF_16));
    }
}
