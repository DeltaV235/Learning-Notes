package com.wuyue.oop;

public class InstrumentTest {
    public static void main(String[] args) {
        Musician musician = new Musician();
        musician.play(new Erhu());
        musician.play(new Piano());
        musician.play(new Violin());
    }
}

abstract class Instrument {
    public abstract void makeSound();
}

class Erhu extends Instrument {
    public void makeSound() {
        System.out.println("I'm Erhu");
    }
}

class Piano extends Instrument {
    @Override
    public void makeSound() {
        System.out.println("I'm Piano");
    }
}

class Violin extends Instrument {
    @Override
    public void makeSound() {
        System.out.println("I'm Violin");
    }
}

class Musician {
    public void play(Instrument i) {
        i.makeSound();
    }
}
