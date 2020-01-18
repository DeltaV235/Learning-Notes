package com.wuyue.annotation.orm;

@Table("tb_students")
public class Student {
    @Field(field = "id", type = "int", length = 10, isNull = false, isPri = true)
    private int id;
    @Field(field = "sname", type = "varchar", length = 10, isNull = false)
    private String name;
    @Field(field = "sage", type = "int", length = 3, isNull = false)
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
