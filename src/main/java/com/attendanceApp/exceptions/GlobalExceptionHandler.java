package com.attendanceApp.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException
            (ResourceNotFoundException  exception, HttpServletRequest req)
    {
        log.warn("Not found: {}", exception.getMessage());

        ErrorResponse error = new ErrorResponse
                (HttpStatus.NOT_FOUND.value(),
                        "Not Found",
                        exception.getMessage(),
                        req.getRequestURI());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidJwtException.class, AccessDeniedException.class})
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
    public ResponseEntity<ErrorResponse> handleNoSuchElementException(DuplicateResourceException exception
    , HttpServletRequest request){
        ErrorResponse error = new ErrorResponse
                (HttpStatus.CONFLICT.value(),
                        "Conflict",
                        exception.getMessage(),
                        request.getRequestURI());

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> randomException
            (Exception exception, HttpServletRequest req){
        ErrorResponse error = new ErrorResponse
                (HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Internal Server Error",
                        exception.getMessage(),
                        req.getRequestURI());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(Exception exception, HttpServletRequest req){

        ErrorResponse errorResponse = new ErrorResponse
                (HttpStatus.BAD_REQUEST.value(), "Bad request",
                        exception.getMessage(), req.getRequestURI());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
