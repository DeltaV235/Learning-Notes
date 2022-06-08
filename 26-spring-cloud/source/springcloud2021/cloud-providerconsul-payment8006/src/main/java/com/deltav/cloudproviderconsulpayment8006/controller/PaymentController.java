package com.deltav.cloudproviderconsulpayment8006.controller;

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

    @GetMapping("/payment/consul")
    public String consulTest() {
        return "Server Port: " + serverPort + "\tUUID: " + UUID.randomUUID();
    }
}
