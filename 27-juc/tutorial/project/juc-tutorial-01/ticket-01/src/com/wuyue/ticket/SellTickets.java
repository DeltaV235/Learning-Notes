package com.wuyue.ticket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author DeltaV235
 * @version 1.0
 * @className com.wuyue.ticket.ticket
 * @description 3 个售票员 出售 30 张票
 * @date 2020/9/5 21:51
 */
@FunctionalInterface
interface Foo {
    int add(int a, int b);

    default double div(double a, double b) {
        return a / b;
    }

    default double mod(int a, int b) {
        return a % b;
    }

    static double mul(double a, double b) {
        return a * b;
    }
}

public class SellTickets {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        int loopCount = 40;

        System.out.println(Foo.mul(4, 5));
        Foo foo = (a, b) -> a + b;
        System.out.println(foo.add(4, 5));
        System.out.println(foo.div(20, 4));

        new Thread(() -> {
            for (int i = 0; i < loopCount; i++) {
                ticket.sellTicket();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < loopCount; i++) {
                ticket.sellTicket();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < loopCount; i++) {
                ticket.sellTicket();
            }
        }, "C").start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < loopCount; i++) {
//                    ticket.sellTicket();
//                }
//            }
//        }, "A").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < loopCount; i++) {
//                    ticket.sellTicket();
//                }
//            }
//        }, "B").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < loopCount; i++) {
//                    ticket.sellTicket();
//                }
//            }
//        }, "C").start();
    }
}

class Ticket {
    private int count = 30;
    private final Lock lock = new ReentrantLock();

    public void sellTicket() {
        lock.lock();
        try {
            if (this.count > 0) {
                System.out.printf("%s:\t卖出第 %d 张\t还剩 %d 张%n", Thread.currentThread().getName(), count--, count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
