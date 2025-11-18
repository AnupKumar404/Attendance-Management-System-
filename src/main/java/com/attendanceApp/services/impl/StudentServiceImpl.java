package com.attendanceApp.services.impl;

import com.attendanceApp.dtos.StudentDTO;
import com.attendanceApp.entities.Student;
import com.attendanceApp.exceptions.ResourceNotFoundException;
import com.attendanceApp.repositories.StudentRepository;
import com.attendanceApp.services.StudentService;
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
                .orElseThrow(()
                        -> new ResourceNotFoundException("Student with id "+id+" not found"));
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(stu -> modelMapper.map(stu, StudentDTO.class))
                .collect(Collectors.toList());
    }
}