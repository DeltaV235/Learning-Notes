package com.wuyue.collection;

import java.util.*;

public class StoreDataTestWithJavaBean {
    public static void main(String[] args) {
        User user1 = new User(1000, "WY", 30000, "2019.3.30");
        User user2 = new User(1001, "KWY", 20000, "2016.8.5");
        User user3 = new User(1002, "KKR", 15000, "2018.1.10");
        List<User> table = new LinkedList<>();
        table.add(user1);
        table.add(user2);
        table.add(user3);
        for (User row:table) {
            System.out.println(row);
        }

        Map<Integer, User> table2 = new HashMap<>();
        table2.put(user1.getId(), user1);
        table2.put(user2.getId(), user2);
        table2.put(user3.getId(), user3);
        Set<Integer> keySet = table2.keySet();
        for (Integer key : keySet) {
            System.out.println(key + " = " + table2.get(key));
        }
        System.out.println(table);
        System.out.println(table2);
    }
}

class User {
    private int id;
    private String name;
    private double salary;
    private String hireDate;

    public User(int id, String name, double salary, String hireDate) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDate='" + hireDate + '\'' +
                '}';
    }
}
