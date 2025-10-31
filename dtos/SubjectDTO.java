package com.spring.attendanceApp.dtos;

import lombok.Data;

@Data
public class SubjectDTO {
    private String name;
    private String code;
    private Long teacherId;
}
