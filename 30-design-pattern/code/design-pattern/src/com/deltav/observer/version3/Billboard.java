package com.deltav.observer.version3;

public class Billboard extends StockObserver {

    public Billboard(Stock stock) {
        super(stock);
    }

    public void display(int price) {
        System.out.println("Billboard: The price is: " + price);
    }

    public void update() {
        display(super.stock.getPrice());
    }
}
