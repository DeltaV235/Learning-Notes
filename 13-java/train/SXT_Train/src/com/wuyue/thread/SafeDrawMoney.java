package com.wuyue.thread;

public class SafeDrawMoney implements Runnable {
    private final Account account;
    private double drawMoney;

    public SafeDrawMoney(Account account, double drawMoney) {
        this.account = account;
        this.drawMoney = drawMoney;
    }

    public void drawing() {
        if (account.getMoney() > 0) {
            synchronized (account) {
                if (account.getMoney() - drawMoney >= 0) {
                    account.setMoney(account.getMoney() - drawMoney);
                    System.out.println("取出： " + drawMoney);
                    System.out.println("余额为： " + account.getMoney());
                } else {
                    System.out.println("余额不足");
                    System.out.println("余额为： " + account.getMoney());
                }
            }
        }
    }

    @Override
    public void run() {
        drawing();
    }

    public static void main(String[] args) {
        Account account = new Account("wuyue", 2000000);
        new Thread(new SafeDrawMoney(account, 1500000)).start();
        new Thread(new SafeDrawMoney(account, 500000)).start();
        new Thread(new SafeDrawMoney(account, 2000000)).start();
    }
}

class Account {
    private String name;
    private double money;

    public Account(String name, double money) {
        this.name = name;
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
