package com.deltav.observer.version2;

public class Billboard implements StockObserver {

    public void displayBillboard(int price) {
        System.out.println("Billboard: The price is: " + price);
    }

    @Override
    public void update(int price) {
        displayBillboard(price);
    }
}
