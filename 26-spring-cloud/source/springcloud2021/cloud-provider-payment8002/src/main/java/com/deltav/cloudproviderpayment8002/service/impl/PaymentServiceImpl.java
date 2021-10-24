package com.deltav.cloudproviderpayment8002.service.impl;

import com.deltav.cloudproviderpayment8002.dao.PaymentDao;
import com.deltav.cloudproviderpayment8002.service.PaymentService;
import com.deltav.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author DeltaV235
 * @version 1.0
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public Long addPayment(Payment payment) {
        return paymentDao.add(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.queryById(id);
    }
}
