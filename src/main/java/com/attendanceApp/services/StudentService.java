package com.attendanceApp.services;

import com.attendanceApp.dtos.StudentDTO;

import java.util.List;

public interface StudentService {
    StudentDTO createStudent(StudentDTO dto);
    StudentDTO getStudentById(Long id);
    List<StudentDTO> getAllStudents();
}
