package com.deltav;

interface Hunter {
    void hunt();
}

class Animal {
    public void eat() {
        System.out.println("animal eat!");
    }
}

class Cat extends Animal implements Hunter {

    public Cat() {
        super();
    }

    public Cat(String name) {
        this();
    }

    @Override
    public void eat() {
        System.out.println("cat eat rat!");
    }

    @Override
    public void hunt() {
        System.out.println("cat hunt nonthing!");
    }
}

class Dog extends Animal implements Hunter {
    @Override
    public void eat() {
        System.out.println("Dog eat meat");
    }

    @Override
    public void hunt() {
        System.out.println("Dog hunt noting");
    }
}

/**
 * two Method bundle type
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/4/10 21:20
 */
public class MethodBundleDemo {
    public void showAnimal(Animal animal) {
        animal.eat();
    }

    public void showHunt(Hunter hunter) {
        hunter.hunt();
    }
}
