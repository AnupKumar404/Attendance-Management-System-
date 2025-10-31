package com.spring.attendanceApp.exceptions;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@NoArgsConstructor
public class Error {
    @CurrentTimestamp
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
