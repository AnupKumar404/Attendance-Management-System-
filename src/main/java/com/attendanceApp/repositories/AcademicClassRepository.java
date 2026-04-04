package com.attendanceApp.repositories;

import com.attendanceApp.entities.AcademicClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademicClassRepository extends JpaRepository<AcademicClass, Long> {

}
