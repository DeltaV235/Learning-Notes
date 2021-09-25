package com.deltav.controller;

import com.deltav.entities.Payment;
import com.deltav.service.PaymentService;
import com.deltav.vo.CommonResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author DeltaV235
 * @version 1.0
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @GetMapping("/payment/{id}")
    public CommonResultVO<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment;
        try {
            payment = paymentService.getPaymentById(id);
            return CommonResultVO.successWithData(payment);
        } catch (Exception e) {
            log.error("get payment info failed", e);
            return CommonResultVO.failedWithoutData();
        }
    }

    @PostMapping("/payment")
    public CommonResultVO<Long> addPayment(@RequestBody Payment payment) {
        try {
            Long retVal = paymentService.addPayment(payment);
            return CommonResultVO.successWithData(retVal);
        } catch (Exception e) {
            log.error("insert payment info failed", e);
            return CommonResultVO.failedWithoutData();
        }
    }
}
