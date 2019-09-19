package com.return404.Company;

public class Manager extends Employee {
    private double bonus;

    Manager(String name, double salary) {
        super(name, salary);
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    @Override
    public double getSalary() {
        return super.getSalary() + this.getBonus();
    }

    public static void main(String[] args) {
        Manager manager = new Manager("wuyue", 5000);
        manager.setBonus(2000);
        System.out.println("Manager's bonus is " + manager.getBonus() + "\nAnd Salary is " + manager.getSalary());
    }
}
