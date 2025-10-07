package com.spring.attendanceApp.services;

import com.spring.attendanceApp.dtos.TeacherDTO;

import java.util.List;

public interface TeacherService {
    TeacherDTO createTeacher(TeacherDTO dto);
    TeacherDTO getTeacherById(Long id);
    List<TeacherDTO> getAllTeachers();
}
