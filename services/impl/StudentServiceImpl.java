package com.spring.attendanceApp.services.impl;

import com.spring.attendanceApp.dtos.StudentDTO;
import com.spring.attendanceApp.entities.Student;
import com.spring.attendanceApp.repositories.StudentRepository;
import com.spring.attendanceApp.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    @Override
    public StudentDTO createStudent(StudentDTO dto) {
        Student student = modelMapper.map(dto, Student.class);
        Student saved = studentRepository.save(student);
        return modelMapper.map(saved, StudentDTO.class);
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow();
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(stu -> modelMapper.map(stu, StudentDTO.class))
                .collect(Collectors.toList());
    }
}
