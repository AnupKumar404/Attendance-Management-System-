package com.spring.attendanceApp.repositories;

import com.spring.attendanceApp.entities.AttendanceSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceSessionRepository extends JpaRepository<AttendanceSession, Long> {
    List<AttendanceSession> findByDate(LocalDate date);
//    List<AttendanceSession> findBySubjectIdAndTeacherId(Long subjectId, Long teacherId);
}
