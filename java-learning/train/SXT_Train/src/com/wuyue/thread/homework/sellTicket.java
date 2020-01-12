package com.wuyue.thread.homework;

import java.util.concurrent.atomic.AtomicInteger;

public class sellTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket(100);
        new Thread(new sellMachine(ticket), "浦东").start();
        new Thread(new sellMachine(ticket), "松江").start();
        new Thread(new sellMachine(ticket), "黄埔").start();
        new Thread(new sellMachine(ticket), "徐汇").start();
        new Thread(new sellMachine(ticket), "南市").start();
    }
}

class Ticket {
    private AtomicInteger tickets;

    public Ticket(int num) {
        tickets = new AtomicInteger(num);
    }

    public void sell() {
        if (tickets.get() > 0) {
            int temp = tickets.decrementAndGet();
            System.out.println(Thread.currentThread().getName() + "卖出一张票，剩余" + temp + "张");
        }
    }

    public int getTickets() {
        return tickets.get();
    }
}

class sellMachine implements Runnable {
    Ticket ticket;

    public sellMachine(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public void run() {
        while (ticket.getTickets() != 0) {
            ticket.sell();
            Thread.yield();
        }
    }
}
