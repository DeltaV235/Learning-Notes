package com.wuyue.domain;

import java.util.List;
import java.util.Map;

/**
 * @author DeltaV235
 * @version 1.0
 * @className Person
 * @description
 * @date 2020/3/11 15:31
 */
public class Person {

    private String name = "wuyue";
    private Integer age;
    private String gender;
    private String email;

    private Car car;
    private List<Book> books;
    private Map<Object, Object> maps;

    public Person() {
        System.out.println("默认构造器 " + super.toString());
    }

    public Person(String name, Integer age, String gender, String email) {
        System.out.println("有参构造器 " + super.toString());
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
    }

    public Map<Object, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<Object, Object> maps) {
        this.maps = maps;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", car=" + car +
                ", books=" + books +
                ", maps=" + maps +
                '}';
    }
}















