package org.example.schoolmanagement.utils;

public class BusinessException extends RuntimeException {
    private final int code;
    private String details;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }
    public BusinessException(int code, String message, String details) {
        super(message);
        this.code = code;
        this.details = details;
    }

    public int getCode() {
        return code;
    }

    public String getDetails() {
        return details;
    }
}
