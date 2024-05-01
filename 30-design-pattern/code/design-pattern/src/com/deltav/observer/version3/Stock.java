package com.deltav.observer.version3;

public class Stock extends Subject {
    private int price;

    public Stock(int price) {
        this.price = price;
    }

    public void setPrice(int price) {
        this.price = price;
        notifyObservers();
    }

    public int getPrice() {
        return price;
    }

    public static void main(String[] args) {
        Stock stock = new Stock(10);
        new Monitor(stock);
        new Billboard(stock);
        stock.setPrice(200);
        stock.setPrice(10);
    }
}
