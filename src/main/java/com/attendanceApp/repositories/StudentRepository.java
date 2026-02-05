package com.attendanceApp.repositories;

import com.attendanceApp.dtos.StudentDTO;
import com.attendanceApp.entities.Student;
import com.attendanceApp.projections.StudentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "SELECT full_name AS fullName, " +
            "roll_no AS rollNo, batch AS Batch FROM students WHERE id = :id",
            nativeQuery = true)
    Optional<StudentProjection> findStudentById(@Param("id") Long id);

    @Query(value = "SELECT full_name AS fullName, " +
            "roll_no AS rollNo, batch AS Batch FROM students WHERE roll_no = :rollNo",
            nativeQuery = true)
    Optional<StudentProjection> findByRollNo(@Param("rollNo") String rollNo);
}
