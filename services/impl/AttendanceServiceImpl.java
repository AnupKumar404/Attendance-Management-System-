package com.spring.attendanceApp.services.impl;

import com.spring.attendanceApp.entities.AttendanceRecord;
import com.spring.attendanceApp.entities.AttendanceSession;
import com.spring.attendanceApp.entities.Student;
import com.spring.attendanceApp.enums.AttendanceStatus;
import com.spring.attendanceApp.repositories.AttendanceRecordRepository;
import com.spring.attendanceApp.repositories.AttendanceSessionRepository;
import com.spring.attendanceApp.repositories.StudentRepository;
import com.spring.attendanceApp.repositories.SubjectRepository;
import com.spring.attendanceApp.services.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final AttendanceRecordRepository attendanceRecordRepository;
    private final AttendanceSessionRepository attendanceSessionRepository;

    @Override
    public AttendanceRecord markAttendance(Long studentId, Long sessionId, AttendanceStatus status) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow();

        AttendanceSession session = attendanceSessionRepository.findById(sessionId)
                .orElseThrow();

        // Prevent duplicate attendance entry for same student & date & subject
        Optional<AttendanceRecord> existing = attendanceRecordRepository
                .findByStudentAndSession(student, session);

        if(existing.isPresent()){
            throw new RuntimeException("Attendance already marked for this student on this day");
        }

        AttendanceRecord attendanceRecord = new AttendanceRecord();
        attendanceRecord.setStudent(student);
        attendanceRecord.setSession(session);
        attendanceRecord.setStatus(status);

        return attendanceRecordRepository.save(attendanceRecord);
    }

    @Override
    public AttendanceRecord updateAttendance(Long attendanceId, AttendanceStatus status) {
        AttendanceRecord record = attendanceRecordRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance record not found"));
        record.setStatus(status);
        return attendanceRecordRepository.save(record);
    }

    @Override
    public List<AttendanceRecord> getAttendanceByStudent(Long studentId) {
        return attendanceRecordRepository.findByStudentId(studentId);
    }

    @Override
    public List<AttendanceRecord> getAttendanceBySubject(Long subjectId) {
        return attendanceRecordRepository.findBySubjectId(subjectId);
    }

    @Override
    public List<AttendanceRecord> getAttendanceBySession(Long sessionId) {
        return attendanceRecordRepository.findBySessionId(sessionId);
    }

    @Override
    public double getAttendancePercentage(Long studentId, Long subjectId) {
        long total = attendanceRecordRepository.countByStudentIdAndSubjectId(studentId, subjectId);
        long present = attendanceRecordRepository.countByStudentIdAndSession_SubjectIdAndStatus(studentId, subjectId, AttendanceStatus.PRESENT);
        return total == 0 ? 0.0 : (present * 100.0) / total;
    }

    @Override
    public List<Student> getDefaulters(Long subjectId, double threshold) {
//        List<Student> all = subjectRepository.findById(subjectId)
//                .orElseThrow(() -> new RuntimeException("Subject Not found"))
//                .get;
        return List.of();
    }
}