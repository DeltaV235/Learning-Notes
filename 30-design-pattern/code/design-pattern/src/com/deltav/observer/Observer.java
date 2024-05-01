package com.deltav.observer;

public abstract class Observer<T extends Subject> {
    protected T subject;

    public Observer(T subject) {
        this.subject = subject;
        subject.addObserver(this);
    }

    abstract void update();
}
