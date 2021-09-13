package com.deltav.service;

import com.deltav.entities.Payment;

/**
 * @author DeltaV235
 * @version 1.0
 * @date 2021/9/14 1:05
 */
public interface PaymentService {
    Long addPayment(Payment payment);

    Payment getPaymentById(Long id);
}
