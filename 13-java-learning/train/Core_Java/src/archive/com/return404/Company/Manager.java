package archive.com.return404.Company;

import java.util.ArrayList;

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

    public String getDescription() {
        return String.format("an manager with a salary of %.2f", this.getSalary());
    }

    public static void main(String[] args) {
        ArrayList<Person> personArrayList = new ArrayList<>();
        personArrayList.add(new Manager("wuyue", 5000));
        personArrayList.add(new Manager("David", 3000));
        personArrayList.trimToSize();
        for (Person p: personArrayList) {
            System.out.printf("Description: %s\n", p.getDescription());
        }
    }
}
