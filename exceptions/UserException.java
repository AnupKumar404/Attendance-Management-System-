package com.spring.attendanceApp.exceptions;

import org.springframework.http.HttpStatus;

public record UserException(String message, Throwable throwable, HttpStatus httpStatus) {

}
