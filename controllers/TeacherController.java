package com.spring.attendanceApp.controllers;

import com.spring.attendanceApp.dtos.TeacherDTO;
import com.spring.attendanceApp.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody TeacherDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.createTeacher(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacher(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.getTeacherById(id));
    }

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAllTeacher(){
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.getAllTeachers());
    }
}
