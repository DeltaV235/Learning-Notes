package inherit.equals;

import java.time.LocalDate;
import java.util.Objects;

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

    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null)
            return false;
        if (getClass() != otherObject.getClass())
            return false;
        Employee other = (Employee) otherObject;
        return Objects.equals(this.name, other.name) && this.salary == other.salary && Objects.equals(this.hireDay, other.hireDay);
    }

    public int hashCode() {
        return Objects.hash(this.name, this.salary, this.hireDay);
    }

    public String toString() {
        return getClass().getName() + "[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
    }
}
