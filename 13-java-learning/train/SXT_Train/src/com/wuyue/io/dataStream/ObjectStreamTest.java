package com.wuyue.io.dataStream;

import java.io.*;

public class ObjectStreamTest {
    public static void main(String[] args) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream("Test/data/Employee.data")));
            oos.writeInt(18);
            oos.writeUTF("Boom");
            oos.writeBoolean(true);
            oos.writeObject(new Employee("WY", 30000));
            oos.flush();

            ObjectInputStream ois = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream("Test/data/Employee.data")));
            int age = ois.readInt();
            String name = ois.readUTF();
            boolean flag = ois.readBoolean();
            System.out.println(age + " " + name + " " + flag);
            Object emp = ois.readObject();
            if (emp instanceof Employee) {
                System.out.println("name " + ((Employee) emp).getName() + " salary " + ((Employee) emp).getSalary());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class Employee implements Serializable{
    private String name;
    private transient double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
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
}
