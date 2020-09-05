package inherit.inheritance;

import java.time.LocalDate;

public class Employee {
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        this.hireDay = LocalDate.of(year, month, day);
    }

    public String getName() {
        return name;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public double getSalary() {
        return salary;
    }

    public void raiseSalary(double byPercent) {
        this.salary *= (1 + byPercent / 100);
    }
}
