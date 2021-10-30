package com.deltav.cloudproviderpayment8004.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author DeltaV235
 * @version 1.0
 */
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/zk")
    public String zkTest() {
        return "Server Port: " + serverPort + "\tUUID: " + UUID.randomUUID();
    }
}
