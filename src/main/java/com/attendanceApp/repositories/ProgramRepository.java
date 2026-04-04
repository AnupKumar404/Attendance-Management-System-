package com.attendanceApp.repositories;

import com.attendanceApp.entities.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {

    Boolean existsByDepartmentIdAndName(Long id, String name);
}
