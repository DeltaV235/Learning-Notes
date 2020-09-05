package com.wuyue.thread;

import java.util.HashMap;
import java.util.Map;

public class PriorityTest {
    private static int rank;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            rank = 1;
            Thread t1 = new Thread(new MyPriority(), "10");
            Thread t2 = new Thread(new MyPriority(), "9");
            Thread t3 = new Thread(new MyPriority(), "7");
            Thread t4 = new Thread(new MyPriority(), "5");
            Thread t5 = new Thread(new MyPriority(), "4");
            Thread t6 = new Thread(new MyPriority(), "2");
            Thread t7 = new Thread(new MyPriority(), "1");

            t1.setPriority(Thread.MAX_PRIORITY);
            t2.setPriority(9);
            t3.setPriority(7);
            t4.setPriority(5);
            t5.setPriority(4);
            t6.setPriority(2);
            t7.setPriority(1);

            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();
            t6.start();
            t7.start();
        }

        MyPriority p1 = new MyPriority();
        p1.result();
    }

    public static int returnRank() {
        if (rank == 7) {
            rank = 1;
        }
        return rank++;
    }

}

class MyPriority implements Runnable {
    private static Map<String, Integer> rank = new HashMap<>();

    static {
        rank.put("1", 0);
        rank.put("2", 0);
        rank.put("4", 0);
        rank.put("5", 0);
        rank.put("7", 0);
        rank.put("9", 0);
        rank.put("10", 0);
    }

    @Override
    public void run() {
        rank.put(Thread.currentThread().getName(), (PriorityTest.returnRank() + rank.get(Thread.currentThread().getName())));
    }

    public void result() {
        Thread thread = Thread.currentThread();
        System.out.println(rank);
    }

}
