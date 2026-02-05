package com.attendanceApp.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException
            (ResourceNotFoundException  exception, HttpServletRequest req)
    {
        log.warn("Not found: {}", exception.getMessage());

        ErrorResponse error = new ErrorResponse
                (HttpStatus.NOT_FOUND.value(),
                        "Not_Found",
                        exception.getMessage(),
                        req.getRequestURI());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException
            (MethodArgumentNotValidException exception, HttpServletRequest req)
    {
        log.warn("Not Valid: {}", exception.getMessage());

        ErrorResponse error = new ErrorResponse
                (HttpStatus.BAD_REQUEST.value(),
                        "Invalid_Input",
                        exception.getMessage(),
                        req.getRequestURI());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidJwtException.class)
    public ResponseEntity<ErrorResponse> authenticationException
            (InvalidJwtException  authenticationException, HttpServletRequest req)
    {
        ErrorResponse error = new ErrorResponse
                (HttpStatus.UNAUTHORIZED.value()
                        , "Unauthorized",
                        authenticationException.getMessage()
                , req.getRequestURI());

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateElementException(DuplicateResourceException exception
    , HttpServletRequest request){
        ErrorResponse error = new ErrorResponse
                (HttpStatus.CONFLICT.value(),
                        "Conflict",
                        exception.getMessage(),
                        request.getRequestURI());

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> generalException
            (Exception exception, HttpServletRequest req){
        ErrorResponse error = new ErrorResponse
                (HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Internal_Server_Error",
                        exception.getMessage(),
                        req.getRequestURI());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}