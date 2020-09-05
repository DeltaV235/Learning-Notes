package com.wuyue.springboot07.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author DeltaV235
 * @version 1.0
 * @className MyCommandLineRunner
 * @description
 * @date 2020/4/11 22:04
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("MyCommandLineRunner...run...");
    }
}
