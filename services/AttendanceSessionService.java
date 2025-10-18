package com.spring.attendanceApp.services;

import com.spring.attendanceApp.dtos.AttendanceSessionDTO;

import java.util.List;

public interface AttendanceSessionService {
    AttendanceSessionDTO createSession(Long subjectId, AttendanceSessionDTO dto);
    AttendanceSessionDTO getSessionById(Long id);
    List<AttendanceSessionDTO> getAllSessions();
    AttendanceSessionDTO updateSession(Long sessionId, AttendanceSessionDTO dto);
}
