package com.deltav.observer.version4;

public abstract class Observer<T extends Subject> {
    protected T subject;

    public Observer(T subject) {
        this.subject = subject;
        subject.addObserver(this);
    }

    abstract void update();
}
