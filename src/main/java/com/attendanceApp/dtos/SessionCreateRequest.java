package com.attendanceApp.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SessionCreateRequest {
    private Long subjectId;

    private Long teacherId;

    private LocalDate date;

    private String topic;
}
