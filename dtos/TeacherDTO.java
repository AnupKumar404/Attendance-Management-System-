package com.spring.attendanceApp.dtos;

import lombok.Data;

import java.util.List;

@Data
public class TeacherDTO {
    private Long id;
    private String name;
    private String rollNumber;
    private String department;
    private List<Long> teacherIds;
}
