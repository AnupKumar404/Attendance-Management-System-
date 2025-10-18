package com.spring.attendanceApp.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class Error {
    LocalDateTime timeStamp;
    String message;
    HttpStatus httpStatus;

    public Error(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Error(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
