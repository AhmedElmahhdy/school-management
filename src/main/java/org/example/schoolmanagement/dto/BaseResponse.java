package org.example.schoolmanagement.dto;

public class BaseResponse<T> {
    private int statusCode;      // e.g., "SUCCESS", "VALIDATION_ERROR", "DB_ERROR"
    private String statusMessage;   // human-readable message
    private String details;   // optional (stacktrace, field name, etc.)
    private T data;           // the actual payload (object, list, etc.)

    public BaseResponse(int statusCode, String statusMessage, String details, T data) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.details = details;
        this.data = data;
    }

    public BaseResponse() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
