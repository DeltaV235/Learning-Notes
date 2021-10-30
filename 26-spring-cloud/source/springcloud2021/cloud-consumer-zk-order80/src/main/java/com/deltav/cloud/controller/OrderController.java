package com.deltav.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author DeltaV235
 * @version 1.0
 */
@RestController
@Slf4j
public class OrderController {
    private static final String INVOKE_URL = "http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/zk")
    public String payment() {
        return restTemplate.getForObject(INVOKE_URL + "/payment/zk", String.class);
    }
}
