package org.example.schoolmanagement.utils;

public class handleAccessDeniedException extends RuntimeException {
    public handleAccessDeniedException(String message) {
        super(message);
    }
}