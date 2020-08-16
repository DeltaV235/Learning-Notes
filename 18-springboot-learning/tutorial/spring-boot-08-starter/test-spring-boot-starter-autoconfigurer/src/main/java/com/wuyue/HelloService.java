package com.wuyue;

/**
 * @author DeltaV235
 * @version 1.0
 * @className HelloService
 * @description
 * @date 2020/4/11 23:30
 */
public class HelloService {
    private final ServiceProperty serviceProperty;

    public HelloService(ServiceProperty serviceProperty) {
        this.serviceProperty = serviceProperty;
    }

    public String getName(String name) {
        return serviceProperty.getPrefix() + "-" + name + "-" + serviceProperty.getSuffix();
    }
}
