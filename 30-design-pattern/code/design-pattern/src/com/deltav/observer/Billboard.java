package com.deltav.observer;

public class Billboard extends Observer<Stock> {

    public Billboard(Stock stock) {
        super(stock);
    }

    public void display(int price) {
        System.out.println("Billboard: The price is: " + price);
    }

    public void update() {
        display(super.subject.getPrice());
    }
}
