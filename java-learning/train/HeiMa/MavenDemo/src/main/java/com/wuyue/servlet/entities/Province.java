package com.wuyue.servlet.entities;

/**
 * @author DeltaV235
 * @version 1.0
 * @className Province
 * @description
 * @date 2020/2/25 1:28
 */
public class Province {
    private int id;
    private String name;

    public Province() {
    }

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

    @Override
    public String toString() {
        return "Province{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
