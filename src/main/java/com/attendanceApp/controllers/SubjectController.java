package com.attendanceApp.controllers;

import com.attendanceApp.dtos.SubjectDTO;
import com.attendanceApp.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PutMapping("/update/{id}")
    public ResponseEntity<SubjectDTO> updateSubject(@PathVariable Long id, @RequestBody SubjectDTO dto){
        return ResponseEntity.ok(subjectService.updateSubject(id, dto));
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<SubjectDTO> updateSubject2(@PathVariable Long id, @RequestBody Map<String, Object> dto){
        return ResponseEntity.ok(subjectService.updateSubjectPartial(id, dto));
    }
}