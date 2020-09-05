package com.wuyue.factory;

import com.wuyue.domain.Car;

/**
 * @author DeltaV235
 * @version 1.0
 * @className CarInstanceFactory
 * @description
 * @date 2020/3/12 14:34
 */
public class CarInstanceFactory {
    public CarInstanceFactory() {
        System.out.println("实例工厂被创建");
    }

    public Car getCar(Integer price) {
        Car car = new Car();
        car.setCarName("BMW");
        car.setPrice(price);
        return car;
    }
}














