package com.attendanceApp.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class DuplicateResourceException extends RuntimeException{

    public DuplicateResourceException(String message){
        super(message);
    }
}
