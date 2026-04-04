package com.attendanceApp.repositories;

import com.attendanceApp.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Boolean existsByName(String name);
}
