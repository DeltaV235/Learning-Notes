package com.wuyue.util;

/**
 * @author DeltaV235
 * @version 1.0
 * @className AccessForbiddenException
 * @description
 * @date 2020/5/7 13:49
 */
public class AccessForbiddenException extends RuntimeException {
    public AccessForbiddenException() {
        super();
    }

    public AccessForbiddenException(String message) {
        super(message);
    }

    public AccessForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessForbiddenException(Throwable cause) {
        super(cause);
    }

    protected AccessForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
