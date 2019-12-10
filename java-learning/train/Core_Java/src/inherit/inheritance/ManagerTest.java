package inherit.inheritance;

/**
 * This program demonstrates inheritance.
 *
 * @author DeltaV
 * @version 1.0 2019-12-11
 */
public class ManagerTest {
    public static void main(String[] args) {
        Manager boss = new Manager("wuyue", 5000, 2019, 12, 11);
        boss.setBonus(2000);
        Employee[] staff = new Employee[3];
        staff[0] = boss;
//        Error 调用了Employee中不存在的setBonus()方法
//        staff[0].setBonus(2000);
        staff[1] = new Employee("kkr", 3000, 2019, 12, 9);
        staff[2] = new Employee("ksm", 2500, 2019, 11, 5);
        for(Employee e:staff) {
            System.out.println("name " + e.getName() + "\tsalary " + e.getSalary());
        }
    }
}

