package com.spring.attendanceApp.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AttendanceSessionDTO {
    private Long id;
    private LocalDateTime sessionDate;
    private Long subjectId;

    public AttendanceSessionDTO() {
        this.sessionDate = LocalDateTime.now();
    }
}
