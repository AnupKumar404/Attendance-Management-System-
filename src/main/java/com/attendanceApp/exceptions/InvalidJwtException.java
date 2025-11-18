package com.attendanceApp.exceptions;

import lombok.RequiredArgsConstructor;

public class InvalidJwtException extends RuntimeException{

    public InvalidJwtException(String message){
        super(message);
    }

}
