package com.spring.attendanceApp.services;

import com.spring.attendanceApp.dtos.AttendanceSessionDTO;
import com.spring.attendanceApp.dtos.StudentDTO;
import com.spring.attendanceApp.dtos.SubjectDTO;
import com.spring.attendanceApp.entities.AttendanceSession;
import com.spring.attendanceApp.entities.Student;
import com.spring.attendanceApp.entities.Subject;

import java.util.List;

public interface SubjectService {
    SubjectDTO createSubject(SubjectDTO dto);
    SubjectDTO getSubjectById(Long id);
    List<SubjectDTO> getAllSubjects();
    SubjectDTO addStudentToSubject(Long subjectId, Long studentId);
    List<StudentDTO> getStudentsBySubject(Long subjectId);
    List<AttendanceSessionDTO> getSessionsBySubject(Long subjectId);
}
