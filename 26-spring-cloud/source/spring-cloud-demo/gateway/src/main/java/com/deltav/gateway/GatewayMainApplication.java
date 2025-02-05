package com.deltav.gateway;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayMainApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(GatewayMainApplication.class, args);
    }
}
