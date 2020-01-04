package com.wuyue.thread;

import java.util.HashSet;
import java.util.Set;

/**
 * 使用子代理类实现多线程，具体看代码
 *
 * @author DeltaV235
 */
public class HappyCinemaWithThread {
    public static void main(String[] args) {
        Set<Integer> seats = new HashSet<>();
        seats.add(1);
        seats.add(2);
        seats.add(3);
        seats.add(4);
        seats.add(5);
        NewCinema cinema = new NewCinema(seats);

        NewCustomer customer = new NewCustomer(cinema, "wuyue", 1, 2);
        NewCustomer customer2 = new NewCustomer(cinema, "kangwenyuan", 3, 4, 5);
        customer.start();
        customer2.start();

    }
}

class NewCustomer extends Thread {
    Set<Integer> seats;

    public NewCustomer(Runnable cinema, String name, int... seat) {
        super(cinema, name);
        Set<Integer> seats = new HashSet<>();
        for (int s : seat) {
            seats.add(s);
        }
        this.seats = seats;
    }
}

class NewCinema implements Runnable {
    Set<Integer> seats;

    public NewCinema(Set<Integer> seats) {
        this.seats = seats;
    }

    @Override
    public void run() {
        bookTicket(((NewCustomer)Thread.currentThread()).seats);
    }

    private synchronized void bookTicket(Set<Integer> seats) {
        Set<Integer> copy = new HashSet<>(this.seats);
        copy.removeAll(seats);
        System.out.println(Thread.currentThread().getName());
        if (this.seats.size() - copy.size() == seats.size()) {
            this.seats = copy;
            System.out.println("出票成功 " + seats);
            System.out.println("剩余座位为 " + this.seats + "\n");
        } else {
            System.out.println("出票失败，座位号不正确");
        }
    }
}
