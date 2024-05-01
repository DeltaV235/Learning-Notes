package com.deltav.observer.version1;

public class Stock {
    private int price;
    private final Monitor monitor;
    private final Billboard billboard;

    public Stock(int price, Monitor monitor, Billboard billboard) {
        this.price = price;
        this.monitor = monitor;
        this.billboard = billboard;
    }

    public void setPrice(int price) {
        this.price = price;
        monitor.displayMonitor(price);
        billboard.displayBillboard(price);
    }

    public int getPrice() {
        return price;
    }

    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        Billboard billboard = new Billboard();
        Stock stock = new Stock(10, monitor, billboard);
        stock.setPrice(200);
        stock.setPrice(10);
    }
}
