package interfacePackage.interfaceTest;

import java.util.Arrays;

public class EmploySortTest {
    public static void main(String[] args) {
        Employee[] employee = new Employee[3];
        employee[0] = new Employee("WY", 20000, 2020, 3, 20);
        employee[1] = new Employee("AIAI", 15000, 2019, 12, 31);
        employee[2] = new Employee("Kanon", 22000, 2019, 3, 15);

        Arrays.sort(employee);
        for (Employee e : employee) {
            System.out.println(e);
        }
    }
}
