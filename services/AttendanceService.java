package com.spring.attendanceApp.services;

import com.spring.attendanceApp.dtos.AttendanceRecordDTO;
import com.spring.attendanceApp.enums.AttendanceStatus;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {
    // Mark attendance for a session
    List<AttendanceRecordDTO> markAttendance(Long sessionId, List<AttendanceRecordDTO> records);

    // Update attendance for a student
    AttendanceRecordDTO updateAttendance(Long recordId, AttendanceStatus status);

    // Get attendance by student
    List<AttendanceRecordDTO> getAttendanceByStudent(Long studentId);

    // Get attendance by session/date
    List<AttendanceRecordDTO> getAttendanceBySession(Long sessionId);
    List<AttendanceRecordDTO> getAttendanceByDate(LocalDate date);
}
