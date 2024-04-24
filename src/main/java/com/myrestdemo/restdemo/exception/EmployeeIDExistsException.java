package com.myrestdemo.restdemo.exception;

public class EmployeeIDExistsException extends RuntimeException {
    public EmployeeIDExistsException(String message) {
        super(message);
    }

    public EmployeeIDExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
