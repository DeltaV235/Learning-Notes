package com.wuyue.springboot02configuration.model;

/**
 * @author DeltaV235
 * @version 1.0
 * @className Dog
 * @description
 * @date 2020/4/2 16:53
 */
public class Dog {
    private String name;
    private Integer age;

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

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}














