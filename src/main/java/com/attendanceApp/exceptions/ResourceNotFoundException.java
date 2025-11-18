package com.attendanceApp.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message){
        super(message);
    }
}
