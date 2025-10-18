package com.spring.attendanceApp.services.impl;

import com.spring.attendanceApp.dtos.TeacherDTO;
import com.spring.attendanceApp.entities.Teacher;
import com.spring.attendanceApp.repositories.TeacherRepository;
import com.spring.attendanceApp.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServicesImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;

    @Override
    public TeacherDTO createTeacher(TeacherDTO dto) {
        Teacher teacher = modelMapper.map(dto, Teacher.class);
        Teacher saved = teacherRepository.save(teacher);
        return modelMapper.map(saved, TeacherDTO.class);
    }

    @Override
    public TeacherDTO getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow();
        return modelMapper.map(teacher, TeacherDTO.class);
    }

    @Override
    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(teacher -> modelMapper.map(teacher, TeacherDTO.class)).toList();
    }
}