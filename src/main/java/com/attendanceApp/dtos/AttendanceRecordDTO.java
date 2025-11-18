package com.attendanceApp.dtos;

import com.attendanceApp.enums.AttendanceStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AttendanceRecordDTO {
    private Long id;
    private Long studentId;
    private Long sessionId;
    private AttendanceStatus status;
    private LocalDateTime markedAt;
}
