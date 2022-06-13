package com.deltav.cloudhystrixorder80.controller;

import com.deltav.cloudhystrixorder80.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Order controller.
 *
 * @author DeltaV235
 * @version 1.0
 */
@RestController
@RequestMapping("/consumer")
public class OrderController {
    private final PaymentService paymentService;

    @Autowired
    public OrderController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payment/hystrix/{id}")
    String getPayment(@PathVariable("id") String id) {
        return paymentService.getPayment(id);
    }


    @GetMapping("/payment/hystrix/timeout/{id}")
    String getPaymentTimeout(@PathVariable("id") String id) {
        return paymentService.getPaymentTimeout(id);
    }
}
