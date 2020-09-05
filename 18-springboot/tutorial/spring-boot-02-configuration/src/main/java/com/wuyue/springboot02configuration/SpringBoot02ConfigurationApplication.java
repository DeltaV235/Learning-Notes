package com.wuyue.springboot02configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
//@ImportResource(locations = "classpath:test.xml")
public class SpringBoot02ConfigurationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot02ConfigurationApplication.class, args);
    }

}
