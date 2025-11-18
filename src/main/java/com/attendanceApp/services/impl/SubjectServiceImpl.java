package com.attendanceApp.services.impl;

import com.attendanceApp.dtos.SubjectDTO;
import com.attendanceApp.entities.Subject;
import com.attendanceApp.entities.Teacher;
import com.attendanceApp.exceptions.ResourceNotFoundException;
import com.attendanceApp.repositories.AttendanceSessionRepository;
import com.attendanceApp.repositories.StudentRepository;
import com.attendanceApp.repositories.SubjectRepository;
import com.attendanceApp.repositories.TeacherRepository;
import com.attendanceApp.services.SubjectService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        Subject subject = new Subject();
        subject.setName(dto.getName());
        subject.setCode(dto.getCode());
        subject.setTeacher(teacher);

        Subject saved = subjectRepository.save(subject);
        return modelMapper.map(saved, SubjectDTO.class);
    }

    @Override
    public SubjectDTO getSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("subject with id "+id+" not found"));
        return modelMapper.map(subject, SubjectDTO.class);
    }

    @Override
    public List<SubjectDTO> getAllSubjects() {

        return subjectRepository.findAll().stream()
                .map(subject -> modelMapper.map(subject, SubjectDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SubjectDTO updateSubjectPartial(Long id, Map<String, Object> updates) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("subject with id "+id+" not found"));

        updates.forEach((field, value) -> {
            switch(field){
                case "name":
                    subject.setName((String) value);
                    break;

                case "code":
                    subject.setCode((String) value);
                    break;

                case "teacherId":
                    subject.setTeacher((Teacher) value);
                    break;

                default:
                    throw new IllegalArgumentException("Field is not supported");

            }
        });
        Subject saved = subjectRepository.save(subject);
        return modelMapper.map(saved, SubjectDTO.class);
    }

    @Override
    public SubjectDTO updateSubject(Long id, SubjectDTO dto) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("subject with id "+id+" not found"));

        modelMapper.map(dto, subject);
        subject = subjectRepository.save(subject);
        return modelMapper.map(subject, SubjectDTO.class);
    }
}