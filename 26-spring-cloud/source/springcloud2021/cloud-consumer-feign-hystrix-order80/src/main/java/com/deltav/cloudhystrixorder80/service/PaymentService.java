package com.deltav.cloudhystrixorder80.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author DeltaV235
 * @version 1.0
 */
@FeignClient("provider-hystrix-payment")
public interface PaymentService {

    @GetMapping("/payment/hystrix/{id}")
    String getPayment(@PathVariable("id") String id);


    @GetMapping("/payment/hystrix/timeout/{id}")
    String getPaymentTimeout(@PathVariable("id") String id);
}
