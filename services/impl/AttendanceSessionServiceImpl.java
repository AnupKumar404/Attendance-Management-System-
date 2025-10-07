package com.spring.attendanceApp.services.impl;

import com.spring.attendanceApp.dtos.AttendanceSessionDTO;
import com.spring.attendanceApp.entities.AttendanceSession;
import com.spring.attendanceApp.entities.Subject;
import com.spring.attendanceApp.entities.User;
import com.spring.attendanceApp.exceptions.UserNotFoundException;
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
                .orElseThrow(() -> new UserNotFoundException("Not found", null));
        AttendanceSession session = modelMapper.map(dto, AttendanceSession.class);
        session.setSubject(subject);
        AttendanceSession saved = attendanceSessionRepository.save(session);
        return modelMapper.map(saved, AttendanceSessionDTO.class);
    }

    @Override
    public AttendanceSessionDTO getSessionById(Long id) {
        AttendanceSession session = attendanceSessionRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Session not found", null));
        return modelMapper.map(session, AttendanceSessionDTO.class);
    }

    @Override
    public List<AttendanceSessionDTO> getAllSessions() {
        return attendanceSessionRepository.findAll().stream()
                .map(s -> modelMapper.map(s, AttendanceSessionDTO.class))
                .collect(Collectors.toList());
    }
}
