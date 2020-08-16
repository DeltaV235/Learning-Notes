package inherit.arrayList;

import java.util.ArrayList;

public class arrayList {
    public static void main(String[] args) {
        ArrayList<Employee> staff = new ArrayList<>();
        staff.add(new Employee("wuyue", 50000, 2019, 12, 13));
        staff.add(new Employee("kkr", 20000, 2019, 9, 20));

        for (Employee employee : staff)
            employee.raiseSalary(10);

        for (Employee employee : staff) {
            System.out.printf("name:%s\tsalary:%.1f\thireDay:%s\n", employee.getName(), employee.getSalary(), employee.getHireDay());
        }

        System.out.println();
        staff.set(1,new Employee("ksm", 15000, 2019, 3, 15));
        Employee ksm = staff.get(1);
        System.out.printf("name:%s\tsalary:%.1f\thireDay:%s\n", ksm.getName(), ksm.getSalary(), ksm.getHireDay());
        staff.add(new Employee("kkr", 20000, 2019, 9, 20));
        staff.trimToSize();

        System.out.println();
        for (int count = 0; count < staff.size(); count++) {
            Employee employee = staff.get(count);
            System.out.printf("name:%s\tsalary:%.1f\thireDay:%s\n", employee.getName(), employee.getSalary(), employee.getHireDay());
        }
        System.out.println("staff.size():" + staff.size());

        System.out.println();
        staff.remove(0);
        for (Employee employee : staff) {
            System.out.printf("name:%s\tsalary:%.1f\thireDay:%s\n", employee.getName(), employee.getSalary(), employee.getHireDay());
        }
        System.out.println("staff.size():" + staff.size());
    }
}
