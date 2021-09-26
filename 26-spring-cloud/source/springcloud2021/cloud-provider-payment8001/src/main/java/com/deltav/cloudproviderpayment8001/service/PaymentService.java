package com.deltav.cloudproviderpayment8001.service;


import com.deltav.springcloud.entities.Payment;

/**
 * The interface Payment service.
 *
 * @author DeltaV235
 * @version 1.0
 */
public interface PaymentService {
    /**
     * Add payment.
     *
     * @param payment the payment
     * @return insert result
     */
    Long addPayment(Payment payment);

    /**
     * Gets payment by id.
     *
     * @param id the id
     * @return the payment by id
     */
    Payment getPaymentById(Long id);
}
