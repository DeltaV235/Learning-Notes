package com.atguigu.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(basePackages = "com.atguigu.business.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class SeataBusinessMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataBusinessMainApplication.class, args);
    }
}
