package com.spring.attendanceApp.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class StudentDTO {
    private Long id;
    private String name;
    private String rollNumber;
    private String department;
    private Set<Long> subjectIds;
}
