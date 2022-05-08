package com.deltav;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * -Xms10M -Xmx10M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./OOM.hprof
 *
 * @author DeltaV235
 * @version 1.0
 */
public class OOMExample {
    private static final List<byte[]> list = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            byte[] bytes = new byte[1024 * 1024];
            list.add(bytes);

            // 2000 ms interval
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
