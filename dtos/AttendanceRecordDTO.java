package com.spring.attendanceApp.dtos;

import com.spring.attendanceApp.enums.AttendanceStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AttendanceRecordDTO {
    private Long id;
    private AttendanceStatus status;
    private LocalDateTime markedAt;
    private Long studentId;
    private Long sessionId;
}
