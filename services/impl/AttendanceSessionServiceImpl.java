package com.spring.attendanceApp.services.impl;

import com.spring.attendanceApp.entities.AttendanceSession;
import com.spring.attendanceApp.entities.Subject;
import com.spring.attendanceApp.repositories.AttendanceSessionRepository;
import com.spring.attendanceApp.repositories.SubjectRepository;
import com.spring.attendanceApp.services.AttendanceSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceSessionServiceImpl implements AttendanceSessionService {

    private final AttendanceSessionRepository attendanceSessionRepository;
    private final SubjectRepository subjectRepository;

    public List<AttendanceSession> getSession(Long subjectId){
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow();

        return attendanceSessionRepository.findBySubjectId(subjectId);
    }
}
