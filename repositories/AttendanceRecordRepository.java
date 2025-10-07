package com.spring.attendanceApp.repositories;

import com.spring.attendanceApp.entities.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {

    List<AttendanceRecord> findByStudentId(Long studentId);
    List<AttendanceRecord> findBySessionId(Long sessionId);
    @Query("select a from AttendanceRecord a where a.markedAt= :date")
    List<AttendanceRecord> findByDate(@Param("date") LocalDate date);
}
