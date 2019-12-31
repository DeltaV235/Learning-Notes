package com.wuyue.thread;

public class Race implements Runnable {
    private String winner;

    @Override
    public void run() {
        for (int step = 1; step <= 100; step++) {
            if (hasWinner(step))
                break;
            System.out.println(Thread.currentThread().getName() + " ->  step is " + step);
        }
    }

    private boolean hasWinner(int step) {
        if (winner != null)
            return true;
        else {
            if (step == 100) {
                winner = Thread.currentThread().getName();
                System.out.println("Winner is " + winner);
                return true;
            } else
                return false;
        }
    }

    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race, "乌龟").start();
        new Thread(race, "兔子").start();
    }
}
