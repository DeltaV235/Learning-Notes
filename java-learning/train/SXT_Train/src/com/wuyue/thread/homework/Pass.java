package com.wuyue.thread.homework;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Pass {
    public static void main(String[] args) {
        Mountain mountain = new Mountain();
        new Thread(new action(mountain), "wu").start();
        new Thread(new action(mountain), "yue").start();
        new Thread(new action(mountain), "kang").start();
        new Thread(new action(mountain), "wen").start();
        new Thread(new action(mountain), "yuan").start();
        new Thread(new action(mountain), "ko").start();
        new Thread(new action(mountain), "ro").start();
        new Thread(new action(mountain), "chen").start();
        new Thread(new action(mountain), "hui").start();
        new Thread(new action(mountain), "go").start();
    }
}

class Mountain {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private int index = 1;
    private boolean hasPerson = false;

    public void passOnePerson() throws InterruptedException {
        lock.lock();
        while (hasPerson)
            condition.await();
        hasPerson = true;
        System.out.println("第" + index + "位:" + Thread.currentThread().getName() + "开始通过");
        Thread.sleep(5000);
        System.out.println("第" + index + "位:" + Thread.currentThread().getName() + "已通过");
        index++;
        hasPerson = false;
        condition.signalAll();
        lock.unlock();
    }
}

class action implements Runnable {
    private Mountain mountain;

    public action(Mountain mountain) {
        this.mountain = mountain;
    }

    @Override
    public void run() {
        try {
            mountain.passOnePerson();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
