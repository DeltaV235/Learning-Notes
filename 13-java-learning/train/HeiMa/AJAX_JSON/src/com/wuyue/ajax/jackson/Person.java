package com.wuyue.ajax.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author DeltaV235
 * @version 1.0
 * @className JackSonDemo1
 * @description 测试Jackson解析
 * @date 2020/2/23 14:20
 */
public class Person {
    private String name;
    private int age;
    private String gender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    public Date getBirthday() {
        return birthday;
    }

    //    @JsonIgnore
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Person() {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
