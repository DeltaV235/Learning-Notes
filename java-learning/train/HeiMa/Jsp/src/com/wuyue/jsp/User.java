package com.wuyue.jsp;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author DeltaV235
 * @version 1.0
 * @className User
 * @description 测试EL表达式用的类
 * @date 2020/2/18 14:26
 */
public class User {
    private String name;
    private int age;
    private Date birthday;

    public User(String name, int age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public String getBirStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (birthday != null) {
            return simpleDateFormat.format(birthday);
        } else
            return "";
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
