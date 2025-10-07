package com.spring.attendanceApp.dtos;

import com.spring.attendanceApp.entities.AttendanceSession;
import com.spring.attendanceApp.entities.Student;
import com.spring.attendanceApp.enums.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceReqDto {

    private Student student;

    private AttendanceStatus status;

    private LocalDateTime markedAt;

    private AttendanceSession session;
}
