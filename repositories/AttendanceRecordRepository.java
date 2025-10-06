package com.spring.attendanceApp.repositories;

import com.spring.attendanceApp.entities.AttendanceRecord;
import com.spring.attendanceApp.entities.AttendanceSession;
import com.spring.attendanceApp.entities.Student;
import com.spring.attendanceApp.enums.AttendanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {

    Optional<AttendanceRecord> findByStudentAndSession(Student student, AttendanceSession session);

    List<AttendanceRecord> findByStudentId(Long studentId);

    List<AttendanceRecord> findBySessionId(Long sessionId);

    List<AttendanceRecord> findBySubjectId(Long subjectId);

    long countByStudentIdAndSubjectId(Long studentId, Long subjectId);

    long countByStudentIdAndSession_SubjectIdAndStatus(Long studentId, Long sessionId, AttendanceStatus status);
}
