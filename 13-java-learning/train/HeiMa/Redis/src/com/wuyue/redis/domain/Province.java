package com.wuyue.redis.domain;

/**
 * @author DeltaV235
 * @version 1.0
 * @className Province
 * @description Province表对应的实体类
 * @date 2020/2/24 13:26
 */
public class Province {
    private int id;
    private String name;

    public Province(int id, String name) {
        this.id = id;
        this.name = name;
    }

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
