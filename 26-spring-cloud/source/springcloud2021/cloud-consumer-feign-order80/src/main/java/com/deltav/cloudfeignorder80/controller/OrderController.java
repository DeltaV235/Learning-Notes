package com.deltav.cloudfeignorder80.controller;

import com.deltav.cloudfeignorder80.service.PaymentFeignService;
import com.deltav.springcloud.entities.Payment;
import com.deltav.springcloud.vo.CommonResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DeltaV235
 * @version 1.0
 */
@RestController()
@RequestMapping("/consumer")
@Slf4j
public class OrderController {

    private final PaymentFeignService paymentFeignService;

    @Autowired
    public OrderController(PaymentFeignService paymentFeignService) {
        this.paymentFeignService = paymentFeignService;
    }

    @GetMapping("/payment/{id}")
    public CommonResultVO<Payment> getPayment(@PathVariable String id) {
        return paymentFeignService.getPaymentById(Long.valueOf(id));
    }
}
