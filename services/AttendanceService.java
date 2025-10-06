package com.spring.attendanceApp.services;

import com.spring.attendanceApp.entities.AttendanceRecord;
import com.spring.attendanceApp.entities.Student;
import com.spring.attendanceApp.enums.AttendanceStatus;

import java.util.List;

public interface AttendanceService {
    AttendanceRecord markAttendance(Long studentId, Long sessionId, AttendanceStatus status);
    AttendanceRecord updateAttendance(Long attendanceId, AttendanceStatus status);
    List<AttendanceRecord> getAttendanceByStudent(Long studentId);
    List<AttendanceRecord> getAttendanceBySubject(Long subjectId);
    List<AttendanceRecord> getAttendanceBySession(Long sessionId);
    double getAttendancePercentage(Long studentId, Long subjectId);
    List<Student> getDefaulters(Long subjectId, double threshold);
}
