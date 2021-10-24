package com.deltav.cloudproviderpayment8002.controller;

import com.deltav.cloudproviderpayment8002.service.PaymentService;
import com.deltav.springcloud.entities.Payment;
import com.deltav.springcloud.vo.CommonResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author DeltaV235
 * @version 1.0
 */
@RestController
@Slf4j
public class PaymentController {
    private static final String CONTROLLER_DESCRIPTION = "microservice test API for payment module";

    @Resource
    private PaymentService paymentService;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/{id}")
    public CommonResultVO<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment;
        try {
            payment = paymentService.getPaymentById(id);
            return CommonResultVO.successWithData(payment, applicationName, serverPort, CONTROLLER_DESCRIPTION);
        } catch (Exception e) {
            log.error("get payment info failed", e);
            return CommonResultVO.failedWithoutData(applicationName, serverPort, CONTROLLER_DESCRIPTION);
        }
    }

    @PostMapping("/payment")
    public CommonResultVO<Long> addPayment(@RequestBody Payment payment) {
        try {
            Long retVal = paymentService.addPayment(payment);
            return CommonResultVO.successWithData(retVal, applicationName, serverPort, CONTROLLER_DESCRIPTION);
        } catch (Exception e) {
            log.error("insert payment info failed", e);
            return CommonResultVO.failedWithoutData(applicationName, serverPort, CONTROLLER_DESCRIPTION);
        }
    }
}
