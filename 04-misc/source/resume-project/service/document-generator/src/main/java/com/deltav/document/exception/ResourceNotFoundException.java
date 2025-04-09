package com.deltav.document.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s 不存在，%s: %s", resourceName, fieldName, fieldValue));
    }
} 