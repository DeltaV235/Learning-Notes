package com.deltav.cloudhystrixpayment8001.controller;

import com.deltav.cloudhystrixpayment8001.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DeltaV235
 * @version 1.0
 */
@RestController
@Slf4j
public class PaymentController {
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payment/hystrix/{id}")
    public String getPayment(@PathVariable("id") String id) {
        String result = paymentService.getPayment(id);
        log.info(result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "getPaymentTimeoutFallback",
            commandProperties = @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"))
    public String getPaymentTimeout(@PathVariable("id") String id) {
//        int i = 10 / 0;
        String result = paymentService.getPaymentTimeout(id);
        log.info(result);
        return result;
    }

    public String getPaymentTimeoutFallback(String id) {
        return "threadName: " + Thread.currentThread().getName() + " | port: " + port + " | id: " + id + " | Timeout API Fallback";
    }
}
