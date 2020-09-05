package com.wuyue.factory;

import com.wuyue.domain.Car;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author DeltaV235
 * @version 1.0
 * @className CarFactoryWithBean
 * @description
 * @date 2020/3/12 14:43
 */
public class CarFactoryWithBean implements FactoryBean<Car> {
    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        car.setCarName("Benz");
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}














