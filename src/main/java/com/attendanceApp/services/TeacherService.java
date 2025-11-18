package com.attendanceApp.services;

import com.attendanceApp.dtos.RegisterTeacherRequestDto;
import com.attendanceApp.dtos.TeacherDTO;
import com.attendanceApp.dtos.TeacherResponseDto;

import java.util.List;

public interface TeacherService {
    TeacherDTO createTeacher(TeacherDTO dto);
//    TeacherResponseDto registerTeacher(RegisterTeacherRequestDto dto);
    TeacherDTO getTeacherById(Long id);
    List<TeacherDTO> getAllTeachers();
}
