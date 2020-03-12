package com.wuyue.domain;

/**
 * @author DeltaV235
 * @version 1.0
 * @className Car
 * @description
 * @date 2020/3/11 18:18
 */
public class Car {
    private String carName;
    private Integer price;

    public Car() {
        System.out.println("默认构造器 " + super.toString());
    }

    @Override
    public String toString() {
        return "Car{" +
                "carName='" + carName + '\'' +
                ", price=" + price +
                '}';
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String superToString() {
        return super.toString();
    }
}














