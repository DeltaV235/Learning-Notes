package com.wuyue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author DeltaV235
 * @version 1.0
 * @className HelloServiceAutoConfiguration
 * @description
 * @date 2020/4/11 23:32
 */
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(ServiceProperty.class)
public class HelloServiceAutoConfiguration {
    @Autowired
    private ServiceProperty serviceProperty;

    @Bean
    public HelloService helloService() {
        return new HelloService(serviceProperty);
    }
}
