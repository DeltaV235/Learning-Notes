package com.wuyue.thread;

import java.util.HashSet;
import java.util.Set;

public class HappyCinema {
    public static void main(String[] args) {
        Set<Integer> seats = new HashSet<>();
        seats.add(1);
        seats.add(2);
        seats.add(3);
        seats.add(4);
        seats.add(5);
        Cinema cinema = new Cinema(seats);

        Customer customer = new Customer(cinema, 1, 2);
        Customer customer2 = new Customer(cinema, 10, 4, 5);
        new Thread(customer, "wuyue").start();
        new Thread(customer2, "kangwenyuan").start();


    }
}

class Customer implements Runnable {
    final Cinema cinema;
    Set<Integer> seats;

    public Customer(Cinema cinema, int... seat) {
        Set<Integer> seats = new HashSet<>();
        for (int s : seat) {
            seats.add(s);
        }
        this.cinema = cinema;
        this.seats = seats;
    }

    @Override
    public void run() {
        synchronized (cinema) {
            Set<Integer> copy = new HashSet<>(cinema.seats);
            copy.removeAll(seats);
            System.out.println(Thread.currentThread().getName());
            if (cinema.seats.size() - copy.size() == seats.size()) {
                cinema.seats = copy;
                System.out.println("出票成功 " + seats);
                System.out.println("剩余座位为 " + cinema.seats);
            } else {
                System.out.println("出票失败，座位号不正确");
            }
            System.out.println();
        }
    }
}

class Cinema {
    Set<Integer> seats;

    public Cinema(Set<Integer> seats) {
        this.seats = seats;
    }
}
