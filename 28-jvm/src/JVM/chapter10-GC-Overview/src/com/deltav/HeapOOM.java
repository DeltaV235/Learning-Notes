package com.deltav;

import java.util.ArrayList;

/**
 * -Xms8m -Xmx8m
 * -XX:+HeapDumpOnOutOfMemoryError  这个参数的意思是当程序出现OOM的时候就会在当前工程目录生成一个dump文件
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/9/4 17:37
 */
public class HeapOOM {
    byte[] buffer = new byte[1 * 1024 * 1024];//1MB

    public static void main(String[] args) {
        ArrayList<HeapOOM> list = new ArrayList<>();

        int count = 0;
        try {
            while (true) {
                list.add(new HeapOOM());
                count++;
            }
        } catch (Throwable e) {
            System.out.println("count = " + count);
            e.printStackTrace();
        }
    }
}
