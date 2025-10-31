package com.spring.attendanceApp.controllers;

import com.spring.attendanceApp.dtos.SubjectDTO;
import com.spring.attendanceApp.dtos.TeacherDTO;
import com.spring.attendanceApp.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<SubjectDTO> createSubject(@RequestBody SubjectDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.createSubject(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDTO> getSubject(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.getSubjectById(id));
    }

    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getAllSubject(){
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.getAllSubjects());
    }
}