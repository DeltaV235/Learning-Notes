package com.deltav.observer.version2;

import java.util.ArrayList;
import java.util.List;

public class Stock {
    private int price;
    final private List<StockObserver> observerList = new ArrayList<>();

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

    public void registerObserver(StockObserver observer) {
        observerList.add(observer);
    }

    public void removeObserver(StockObserver observer) {
        observerList.remove(observer);
    }

    public void notifyObservers() {
        for (StockObserver observer : observerList) {
            observer.update(price);
        }
    }

    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        Billboard billboard = new Billboard();
        Stock stock = new Stock(10);

        stock.registerObserver(monitor);
        stock.registerObserver(billboard);

        stock.setPrice(200);
        stock.setPrice(10);
    }
}
