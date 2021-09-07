package com.deltav;

import java.util.ArrayList;
import java.util.List;

/**
 * 该 demo 不严谨，即便不进行 GC，两个线程之间的可能的并发执行也会导致 PrintThread 的时间打印周期大于 1 s。
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/9/7 22:27
 */
public class StopTheWorldDemo {
    public static void main(String[] args) {
        WorkThread workThread = new WorkThread();
        PrintThread printThread = new PrintThread();
        workThread.start();
        printThread.start();
    }

    public static class WorkThread extends Thread {
        List<byte[]> list = new ArrayList<>();

        @Override
        public void run() {
            try {
                while (true) {
                    for (int i = 0; i < 1000; i++) {
                        byte[] buffer = new byte[1024];
                        list.add(buffer);
                    }
                    if (list.size() > 10000) {
                        list.clear();
                        System.gc();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class PrintThread extends Thread {
        public final long startTime = System.currentTimeMillis();

        @Override
        public void run() {
            try {
                while (true) {
                    long t = System.currentTimeMillis() - startTime;
                    System.out.println(t / 1000 + "." + t % 1000);
                    sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
