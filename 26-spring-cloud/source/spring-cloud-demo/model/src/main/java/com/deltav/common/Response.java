package com.deltav.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Response<T> {
    private int code;
    private String message;
    private T data;

    public static <T> Response<T> success() {
        return Response.<T>builder()
                .code(200)
                .message("success")
                .build();
    }

    public static <T> Response<T> success(int code, String message, T data) {
        return Response.<T>builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> Response<T> fail() {
        return Response.<T>builder()
                .code(500)
                .message("fail")
                .build();
    }

    public static <T> Response<T> fail(int code, String message) {
        return Response.<T>builder()
                .code(code)
                .message(message)
                .build();
    }
}
