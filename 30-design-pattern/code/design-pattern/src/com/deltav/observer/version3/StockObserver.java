package com.deltav.observer.version3;

public abstract class StockObserver {
    protected Stock stock;

    public StockObserver(Stock stock) {
        this.stock = stock;
        stock.addObserver(this);
    }

    abstract void update();
}
