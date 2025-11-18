package com.attendanceApp.services;

import com.attendanceApp.dtos.SubjectDTO;

import java.util.List;
import java.util.Map;

public interface SubjectService {
    SubjectDTO createSubject(SubjectDTO dto);
    SubjectDTO getSubjectById(Long id);
    List<SubjectDTO> getAllSubjects();
    SubjectDTO updateSubjectPartial(Long id, Map<String, Object> updates);
    SubjectDTO updateSubject(Long id, SubjectDTO dto);

//    @Query("SELECT s from SUBJECT s ")
//    List<TeacherDTO> getTeacherBySubject(Long id);
//    List<AttendanceSessionDTO> getSessionsBySubject(Long subjectId);
}
