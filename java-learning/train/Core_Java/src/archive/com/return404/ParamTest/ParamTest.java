package archive.com.return404.ParamTest;

public class ParamTest {
    public static void main(String[] args) {
        Employee[] employee = new Employee[2];
        employee[0] = new Employee("DeltaV", 5000);
        employee[1] = new Employee("Brad", 3000);
        tripleSalary(employee[0]);
        swap(employee[0], employee[1]);
        for (Employee e : employee) {
            System.out.println("Name : " + e.getName() + "\tSalary = " + e.getSalary());
        }
//        tripleSalary(employee);
//        System.out.println("Name : " + employee.getName() + "\tSalary = " + employee.getSalary());
    }

    public static void tripleSalary(Employee employee) {
        employee.raiseSalary(200);
    }

    public static void swap(Employee a, Employee b) {
        Employee temp;
        temp = b;
        b = a;
        a = temp;
    }
}

class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void raiseSalary(double byPercent) {
        this.salary *= (1.0 + byPercent / 100.0);
    }
}
