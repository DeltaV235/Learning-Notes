package com.wuyue.springboot02configuration.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author DeltaV235
 * @version 1.0
 * @className person
 * @description
 * @date 2020/4/2 16:51
 */
@ConfigurationProperties(prefix = "person")
//@Validated
@Repository
@PropertySource("classpath:person.properties")
public class Person {
    //    @Value("${person.age}")
//    @Length(min = 5)
    private String lastName;
    //    @Value("#{3+18}")
    private Integer age;
    private boolean employee;

    private Map<String, Object> maps;
    private List<Object> lists;

    private Dog dog;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isEmployee() {
        return employee;
    }

    public void setEmployee(boolean employee) {
        this.employee = employee;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "person{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                ", employee=" + employee +
                ", maps=" + maps +
                ", lists=" + lists +
                ", dog=" + dog +
                '}';
    }
}














