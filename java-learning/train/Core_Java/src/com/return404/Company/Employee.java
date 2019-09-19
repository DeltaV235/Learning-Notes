package com.return404.Company;

import java.time.LocalDate;

public class Employee {
    private String name;
    private double salary;
    private LocalDate hireDay;

    Employee() {
        this.name = "";
        this.salary = 0;
        this.hireDay = LocalDate.now();
    }

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
        this.hireDay = LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }
}
