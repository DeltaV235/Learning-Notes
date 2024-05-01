package com.deltav.observer;

public class Monitor extends StockObserver {
    public Monitor(Stock stock) {
        super(stock);
    }

    public void display(int price) {
        System.out.println("Monitor: The price is: " + price);
    }

    public void update() {
        display(super.stock.getPrice());
    }
}
