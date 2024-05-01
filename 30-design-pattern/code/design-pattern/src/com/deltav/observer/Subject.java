package com.deltav.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    final private List<StockObserver> observerList = new ArrayList<>();

    public void addObserver(StockObserver observer) {
        observerList.add(observer);
    }

    public void removeObserver(StockObserver observer) {
        observerList.remove(observer);
    }

    public void notifyObservers() {
        for (StockObserver observer : observerList) {
             observer.update();
        }
    }
}
