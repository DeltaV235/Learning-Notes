package com.deltav.controller;

import com.deltav.entities.CommonResult;
import com.deltav.entities.Payment;
import com.deltav.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author DeltaV235
 * @version 1.0
 * @date 2021/9/14 1:08
 */
@RestController()
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @GetMapping("/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = null;
        try {
            payment = paymentService.getPaymentById(id);
            return new CommonResult<>(200, "success", payment);
        } catch (Exception e) {
            log.error("get payment info failed", e);
            return new CommonResult<>(401, "failed", null);
        }
    }

    @PostMapping("/payment")
    public CommonResult<Long> addPayment(Payment payment) {
        try {
            Long retVal = paymentService.addPayment(payment);
            return new CommonResult<>(200, "success", retVal);
        } catch (Exception e) {
            log.error("insert payment info failed", e);
            return new CommonResult<>(401, "failed", null);
        }
    }
}
