package com.attendanceApp.dtos;

import com.attendanceApp.enums.AttendanceStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AttendanceRecordDTO {
    private Long studentId;
    private AttendanceStatus status;
    private LocalDateTime markedAt;
}
