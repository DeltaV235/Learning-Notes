package com.deltav.cloudproviderpayment8001.dao;

import com.deltav.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * The interface Payment dao.
 *
 * @author DeltaV235
 * @version 1.0
 */
@Mapper
public interface PaymentDao {
    /**
     * create payment record in DB
     *
     * @param payment entity of payment
     * @return insert result
     */
    Long add(Payment payment);

    /**
     * get payment information by id
     *
     * @param id payment id
     * @return entity payment
     */
    Payment queryById(@Param("id") Long id);
}
