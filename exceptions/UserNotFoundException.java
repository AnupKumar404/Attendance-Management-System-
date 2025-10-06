package com.spring.attendanceApp.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
