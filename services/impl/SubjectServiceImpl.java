package com.spring.attendanceApp.services.impl;

import com.spring.attendanceApp.dtos.AttendanceSessionDTO;
import com.spring.attendanceApp.dtos.StudentDTO;
import com.spring.attendanceApp.dtos.SubjectDTO;
import com.spring.attendanceApp.entities.Student;
import com.spring.attendanceApp.entities.Subject;
import com.spring.attendanceApp.entities.Teacher;
import com.spring.attendanceApp.repositories.AttendanceSessionRepository;
import com.spring.attendanceApp.repositories.StudentRepository;
import com.spring.attendanceApp.repositories.SubjectRepository;
import com.spring.attendanceApp.repositories.TeacherRepository;
import com.spring.attendanceApp.services.SubjectService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final AttendanceSessionRepository attendanceSessionRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public SubjectDTO createSubject(SubjectDTO dto) {
        Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                .orElseThrow();
        Subject subject = modelMapper.map(dto, Subject.class);
        subject.setTeacher(teacher);
//        teacher.setSubjects((List<Subject>) subject);
        Subject saved = subjectRepository.save(subject);
        return modelMapper.map(saved, SubjectDTO.class);
    }

    @Override
    public SubjectDTO getSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow();
        return modelMapper.map(subject, SubjectDTO.class);
    }

    @Override
    public List<SubjectDTO> getAllSubjects() {

        return subjectRepository.findAll().stream()
                .map(subject -> modelMapper.map(subject, SubjectDTO.class))
                .collect(Collectors.toList());
    }
}