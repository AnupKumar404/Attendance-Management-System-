package com.spring.attendanceApp.services.impl;

import com.spring.attendanceApp.dtos.AttendanceSessionDTO;
import com.spring.attendanceApp.entities.Session;
import com.spring.attendanceApp.entities.Subject;
import com.spring.attendanceApp.repositories.AttendanceSessionRepository;
import com.spring.attendanceApp.repositories.SubjectRepository;
import com.spring.attendanceApp.services.AttendanceSessionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceSessionServiceImpl implements AttendanceSessionService {

    private final AttendanceSessionRepository attendanceSessionRepository;

    private final SubjectRepository subjectRepository;

    private final ModelMapper modelMapper;

    @Override
    public AttendanceSessionDTO createSession(Long subjectId, AttendanceSessionDTO dto) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow();
        Session session = modelMapper.map(dto, Session.class);
        session.setSubject(subject);
        Session saved = attendanceSessionRepository.save(session);
        return modelMapper.map(saved, AttendanceSessionDTO.class);
    }

    @Override
    public AttendanceSessionDTO getSessionById(Long id) {
        Session session = attendanceSessionRepository.findById(id)
                .orElseThrow();
        return modelMapper.map(session, AttendanceSessionDTO.class);
    }

    @Override
    public List<AttendanceSessionDTO> getAllSessions() {
        return attendanceSessionRepository.findAll().stream()
                .map(s -> modelMapper.map(s, AttendanceSessionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AttendanceSessionDTO updateSession(Long sessionId, AttendanceSessionDTO dto) {
        Session session = attendanceSessionRepository.findById(sessionId)
                .orElseThrow();

        modelMapper.map(dto, Session.class);
        Session updatedSession = attendanceSessionRepository.save(session);
        return modelMapper.map(updatedSession, AttendanceSessionDTO.class);
    }
}