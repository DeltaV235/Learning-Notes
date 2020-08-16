package archive.com.return404.Company;

import java.time.*;

public class Employee extends Person {
    private String name;
    private double salary;
    private LocalDate hireDay;

    Employee() {
        super("");
        this.salary = 0;
        this.hireDay = LocalDate.now();
    }

    public Employee(String name, double salary) {
        super(name);
        this.salary = salary;
        this.hireDay = LocalDate.now();
    }

    @Override
    public String getDescription() {
        return String.format("an employee with a salary of %.2f", salary);
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

    public void raiseSalary(double rate) {
        this.salary *= (1.0 + rate / 100.0);
    }
}
