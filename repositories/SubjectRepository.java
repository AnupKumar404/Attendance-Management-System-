package com.spring.attendanceApp.repositories;

import com.spring.attendanceApp.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
