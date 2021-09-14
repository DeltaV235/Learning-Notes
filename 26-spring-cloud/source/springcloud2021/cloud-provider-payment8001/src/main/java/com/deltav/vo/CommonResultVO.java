package com.deltav.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.deltav.constant.PaymentConstant.*;

/**
 * @author DeltaV235
 * @version 1.0
 * @date 2021/9/14 0:45
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
