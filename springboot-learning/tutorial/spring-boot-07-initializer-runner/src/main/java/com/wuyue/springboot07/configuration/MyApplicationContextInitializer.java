package com.wuyue.springboot07.configuration;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author DeltaV235
 * @version 1.0
 * @className MyApplicatonContextInitializer
 * @description
 * @date 2020/4/11 21:51
 */
public class MyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("MyApplicationContextInitializer...initializer");
    }
}
