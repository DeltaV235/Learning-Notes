package com.wuyue.springboot02configuration.config;

import com.wuyue.springboot02configuration.model.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author DeltaV235
 * @version 1.0
 * @className DogConfiguration
 * @description
 * @date 2020/4/4 1:52
 */
@Configuration
public class DogConfiguration {
    @Bean
    public Dog dog2() {
        return new Dog();
    }
}














