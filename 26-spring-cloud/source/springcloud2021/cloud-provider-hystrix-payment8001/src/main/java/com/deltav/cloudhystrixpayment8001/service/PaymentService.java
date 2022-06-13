package com.deltav.cloudhystrixpayment8001.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author DeltaV235
 * @version 1.0
 */
@Service
@Slf4j
public class PaymentService {
    @Value("${server.port}")
    private String port;

    public String getPayment(String id) {
        return "threadName: " + Thread.currentThread().getName() + " | port: " + port + " | id: " + id + " | Healthy API";
    }

    public String getPaymentTimeout(String id) {
        final long sleepSecond = 5;
        try {
            TimeUnit.SECONDS.sleep(sleepSecond);
        } catch (InterruptedException e) {
            log.error("sleep interrupted", e);
        }
        return "threadName: " + Thread.currentThread().getName() + " | port: " + port + " | id: " + id + " | Timeout API: " + sleepSecond + " s";
    }
}
