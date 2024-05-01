package com.deltav.observer.version2;

public class Monitor implements StockObserver {

    public void displayMonitor(int price) {
        System.out.println("Monitor: The price is: " + price);
    }

    @Override
    public void update(int price) {
        displayMonitor(price);
    }
}
