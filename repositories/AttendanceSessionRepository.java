package com.spring.attendanceApp.repositories;

import com.spring.attendanceApp.entities.AttendanceSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceSessionRepository extends JpaRepository<AttendanceSession, Long> {
    List<AttendanceSession> findBySubjectId(Long subjectId);
    List<AttendanceSession> findBySubjectIdAndTeacherId(Long subjectId, Long teacherId);
}
