package com.wuyue.thread;

public class StateTest {
    public static void main(String[] args) {
        Thread t = new Thread(()-> System.out.println("----------"));
        Thread.State state = t.getState();
        System.out.println(state);
        t.start();
        while (state != Thread.State.TERMINATED) {
            state = t.getState();
            System.out.print(state + "\t");
            System.out.println(Thread.activeCount());
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        System.out.println(Thread.activeCount());
    }
}
