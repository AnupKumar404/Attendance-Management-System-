package com.spring.attendanceApp.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AttendanceSessionDTO {
    private LocalDateTime sessionDate;
    private String topic;

    public AttendanceSessionDTO() {
        this.sessionDate = LocalDateTime.now();
    }
}
