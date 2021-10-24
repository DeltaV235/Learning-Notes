package com.deltav.cloudconsumerorder80.controller;

import com.deltav.springcloud.entities.Payment;
import com.deltav.springcloud.vo.CommonResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author DeltaV235
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/consumer")
public class OrderController {

    private static final String PAYMENT_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/payment/{id}")
    @SuppressWarnings("unchecked")
    public CommonResultVO<Payment> getPayment(@PathVariable String id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/" + id, CommonResultVO.class);
    }

    @PostMapping("/payment")
    @SuppressWarnings("unchecked")
    public CommonResultVO<Payment> addPayment(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment", payment, CommonResultVO.class);
    }
}
