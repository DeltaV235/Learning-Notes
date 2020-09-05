package com.wuyue.factory;

import com.wuyue.domain.Car;

/**
 * @author DeltaV235
 * @version 1.0
 * @className CarStaticFactory
 * @description
 * @date 2020/3/12 14:37
 */
public class CarStaticFactory {
    public CarStaticFactory() {
        System.out.println("静态工厂被创建");
    }

    public static Car getCar(Integer price) {
        Car car = new Car();
        car.setCarName("BMW");
        car.setPrice(price);
        return car;
    }
}














