package com.spring.attendanceApp.services;

import com.spring.attendanceApp.dtos.SubjectDTO;

import java.util.List;

public interface SubjectService {
    SubjectDTO createSubject(SubjectDTO dto);
    SubjectDTO getSubjectById(Long id);
    List<SubjectDTO> getAllSubjects();
//    List<StudentDTO> getStudentsBySubject(Long subjectId);
//    List<AttendanceSessionDTO> getSessionsBySubject(Long subjectId);
}
