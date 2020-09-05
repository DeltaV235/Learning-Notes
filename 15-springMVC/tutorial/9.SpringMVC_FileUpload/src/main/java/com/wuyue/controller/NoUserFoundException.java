package com.wuyue.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author DeltaV235
 * @version 1.0
 * @className NoUserFoundException
 * @description
 * @date 2020/3/31 15:25
 */
@ResponseStatus(reason = "NoUserFound", code = HttpStatus.FORBIDDEN)
public class NoUserFoundException extends RuntimeException {
}














