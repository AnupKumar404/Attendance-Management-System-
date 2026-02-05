package com.attendanceApp.repositories;

import com.attendanceApp.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("SELECT t.fullName, t.department FROM Teacher t")
    List<Teacher> findAllTeachers();
}
