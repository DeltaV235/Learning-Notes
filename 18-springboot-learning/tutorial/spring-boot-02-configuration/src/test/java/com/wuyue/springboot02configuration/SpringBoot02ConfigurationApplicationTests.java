package com.wuyue.springboot02configuration;

import com.wuyue.springboot02configuration.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class SpringBoot02ConfigurationApplicationTests {

    @Autowired
    private Person person;

    @Autowired
    ApplicationContext ioc;

    @Test
    void testXML() {
        boolean dog = ioc.containsBean("dog2");
        System.out.println(dog);
    }

    @Test
    void contextLoads() {
        System.out.println(person);
    }

}
