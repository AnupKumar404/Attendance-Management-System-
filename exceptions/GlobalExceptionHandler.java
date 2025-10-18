package com.spring.attendanceApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Error> handleUserNotFoundException
            (UsernameNotFoundException  userNotFoundException)
    {
        Error error = new Error
                ("User not found "+userNotFoundException.getMessage(), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(error, error.httpStatus);
    }

//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<>
}
