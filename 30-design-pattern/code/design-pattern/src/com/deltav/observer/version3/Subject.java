package com.deltav.observer.version3;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    final private List<StockObserver> stockObserverList = new ArrayList<>();

    public void addObserver(StockObserver stockObserver) {
        stockObserverList.add(stockObserver);
    }

    public void removeObserver(StockObserver stockObserver) {
        stockObserverList.remove(stockObserver);
    }

    public void notifyObservers() {
        for (StockObserver stockObserver : stockObserverList) {
            stockObserver.update();
        }
    }
}
