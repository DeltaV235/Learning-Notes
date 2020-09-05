package com.wuyue.springboot07.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;

/**
 * @author DeltaV235
 * @version 1.0
 * @className MySpringApplicationRunListener
 * @description
 * @date 2020/4/11 21:54
 */
public class MySpringApplicationRunListener implements SpringApplicationRunListener {
    public MySpringApplicationRunListener(SpringApplication application, String[] args) {
        System.out.println("MySpringApplicationRunListener...initial");
    }

    @Override
    public void starting() {
        System.out.println("MySpringApplicationRunListener...starting...");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println("MySpringApplicationRunListener...environmentPrepared...");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("MySpringApplicationRunListener...contextPrepared...");

    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("MySpringApplicationRunListener...contextLoader...");

    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.println("MySpringApplicationRunListener...started...");

    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println("MySpringApplicationRunListener...running...");

    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("MySpringApplicationRunListener...failed...");

    }
}
