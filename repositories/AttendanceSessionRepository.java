package com.spring.attendanceApp.repositories;

import com.spring.attendanceApp.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceSessionRepository extends JpaRepository<Session, Long> {
//    List<AttendanceSession> findByDate(LocalDateTime date);
//    List<AttendanceSession> findBySubjectIdAndTeacherId(Long subjectId, Long teacherId);
}
