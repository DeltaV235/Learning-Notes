package com.deltav.cloudproviderpayment8001.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.deltav.cloudproviderpayment8001.constant.PaymentConstant.*;

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
        return new CommonResultVO<>(CODE_SUCCESS, MESSAGE_SUCCESS, null);
    }

    public static <E> CommonResultVO<E> successWithData(E data) {
        return new CommonResultVO<>(CODE_SUCCESS, MESSAGE_SUCCESS, data);
    }

    public static <E> CommonResultVO<E> failedWithoutData() {
        return new CommonResultVO<>(CODE_FAILED, MESSAGE_FAILED, null);
    }

    public static <E> CommonResultVO<E> failedWithData(E data) {
        return new CommonResultVO<>(CODE_FAILED, MESSAGE_FAILED, data);
    }
}
