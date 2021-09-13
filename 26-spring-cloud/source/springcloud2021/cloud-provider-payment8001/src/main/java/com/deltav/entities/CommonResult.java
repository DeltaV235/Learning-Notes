package com.deltav.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author DeltaV235
 * @version 1.0
 * @date 2021/9/14 0:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private int code;
    private String message;
    private T data;

    public static CommonResult<Void> successCommonResultWithoutData() {
        return new CommonResult<>(200, "success", null);
    }
}
