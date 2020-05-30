package com.wuyue.util;

/**
 * @author DeltaV235
 * @version 1.0
 * @className LoginFailedException
 * @description 登录失败时Service抛出的异常
 * @date 2020/5/7 1:42
 */
public class LoginFailedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LoginFailedException() {
        super();
    }

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailedException(Throwable cause) {
        super(cause);
    }

    protected LoginFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
