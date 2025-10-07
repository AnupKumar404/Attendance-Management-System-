package com.spring.attendanceApp.repositories;

import com.spring.attendanceApp.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
