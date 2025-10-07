package com.spring.attendanceApp.services.impl;

import com.spring.attendanceApp.dtos.SubjectDTO;
import com.spring.attendanceApp.entities.Subject;
import com.spring.attendanceApp.entities.Teacher;
import com.spring.attendanceApp.exceptions.UserNotFoundException;
import com.spring.attendanceApp.repositories.SubjectRepository;
import com.spring.attendanceApp.repositories.TeacherRepository;
import com.spring.attendanceApp.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;
    @Override
    public SubjectDTO createSubject(SubjectDTO dto) {
        Subject subject = modelMapper.map(dto, Subject.class);
        Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher Not Found"));
        subject.setTeacher(teacher);
        Subject saved = subjectRepository.save(subject);
        return modelMapper.map(saved, SubjectDTO.class);
    }

    @Override
    public SubjectDTO getSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Not found", null));
        return modelMapper.map(subject, SubjectDTO.class);
    }

    @Override
    public List<SubjectDTO> getAllSubjects() {

        return subjectRepository.findAll().stream()
                .map(subject -> modelMapper.map(subject, SubjectDTO.class))
                .collect(Collectors.toList());
    }
}
