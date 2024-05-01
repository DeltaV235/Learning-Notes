package com.deltav.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    final private List<Observer<? extends Subject>> observerList = new ArrayList<>();

    public void addObserver(Observer<? extends Subject> observer) {
        observerList.add(observer);
    }

    public void removeObserver(Observer<? extends Subject> observer) {
        observerList.remove(observer);
    }

    public void notifyObservers() {
        for (Observer<? extends Subject> observer : observerList) {
            observer.update();
        }
    }
}
