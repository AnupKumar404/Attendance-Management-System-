package com.spring.attendanceApp.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class StudentDTO {
    private Long id;
    private String name;
    private String rollNo;
    private String department;
}
