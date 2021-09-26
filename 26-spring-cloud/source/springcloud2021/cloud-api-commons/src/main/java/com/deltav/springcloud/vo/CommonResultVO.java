package com.deltav.springcloud.vo;

import com.deltav.springcloud.constant.PaymentConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author DeltaV235
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResultVO<T> {
    private String code;
    private String message;
    private T data;

    public static <E> CommonResultVO<E> successWithoutData() {
        return new CommonResultVO<>(PaymentConstant.CODE_SUCCESS, PaymentConstant.MESSAGE_SUCCESS, null);
    }

    public static <E> CommonResultVO<E> successWithData(E data) {
        return new CommonResultVO<>(PaymentConstant.CODE_SUCCESS, PaymentConstant.MESSAGE_SUCCESS, data);
    }

    public static <E> CommonResultVO<E> failedWithoutData() {
        return new CommonResultVO<>(PaymentConstant.CODE_FAILED, PaymentConstant.MESSAGE_FAILED, null);
    }

    public static <E> CommonResultVO<E> failedWithData(E data) {
        return new CommonResultVO<>(PaymentConstant.CODE_FAILED, PaymentConstant.MESSAGE_FAILED, data);
    }
}
