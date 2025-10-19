package com.spring.attendanceApp.dtos;

import lombok.Data;

@Data
public class RegisterTeacherRequestDto {

    private String username;
    private String password;
    private String fullName;
    private String department;
}
