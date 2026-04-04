package com.attendanceApp.repositories;

import com.attendanceApp.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

//    List<Students> findByByDesc()
}
