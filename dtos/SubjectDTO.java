package com.spring.attendanceApp.dtos;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class SubjectDTO {
    private Long id;
    private String subjectName;
    private String subjectCode;
    private Long teacherId;
    private Set<Long> studentIds;
    private List<Long> sessionIds;
}
