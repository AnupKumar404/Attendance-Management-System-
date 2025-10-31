package com.spring.attendanceApp.services.impl;

import com.spring.attendanceApp.dtos.AttendanceRecordDTO;
import com.spring.attendanceApp.entities.AttendanceRecord;
import com.spring.attendanceApp.entities.Session;
import com.spring.attendanceApp.entities.Student;
import com.spring.attendanceApp.enums.AttendanceStatus;
import com.spring.attendanceApp.repositories.AttendanceRecordRepository;
import com.spring.attendanceApp.repositories.AttendanceSessionRepository;
import com.spring.attendanceApp.repositories.StudentRepository;
import com.spring.attendanceApp.services.AttendanceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceSessionRepository attendanceSessionRepository;

    private final AttendanceRecordRepository attendanceRecordRepository;

    private final StudentRepository studentRepository;

    private final ModelMapper modelMapper;


    @Override
    public List<AttendanceRecordDTO> markAttendance(Long sessionId, List<AttendanceRecordDTO> records) {
        Session session = attendanceSessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        List<AttendanceRecord> savedRecords = records.stream().map(dto -> {
            Student student = studentRepository.findById(dto.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            AttendanceRecord record = new AttendanceRecord();
            record.setSession(session);
            record.setStudent(student);
            record.setStatus(dto.getStatus());
            record.setMarkedAt(session.getSessionDate());
            return record;
        }).map(attendanceRecordRepository::save).toList();

        return savedRecords.stream()
                .map(r -> modelMapper.map(r, AttendanceRecordDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AttendanceRecordDTO updateAttendance(Long recordId, AttendanceStatus status) {
        AttendanceRecord record = attendanceRecordRepository.findById(recordId)
                .orElseThrow(() -> new RuntimeException("Not found"));
        record.setStatus(status);
        attendanceRecordRepository.save(record);
        return modelMapper.map(record, AttendanceRecordDTO.class);
    }

    @Override
    public List<AttendanceRecordDTO> getAttendanceByStudent(Long studentId) {
        return attendanceRecordRepository.findByStudentId(studentId)
                .stream().map(r -> modelMapper.map(r, AttendanceRecordDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceRecordDTO> getAttendanceBySession(Long sessionId) {
        return attendanceRecordRepository.findBySessionId(sessionId).stream()
                .map(r -> modelMapper.map(r, AttendanceRecordDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceRecordDTO> getAttendanceByDate(LocalDate date) {
        return attendanceRecordRepository.findByDate(date).stream()
                .map(r -> modelMapper.map(r, AttendanceRecordDTO.class))
                .collect(Collectors.toList());
    }
}