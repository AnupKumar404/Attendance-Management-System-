package com.attendanceApp.controllers;

import com.attendanceApp.dtos.StudentDTO;
import com.attendanceApp.projections.StudentProjection;
import com.attendanceApp.services.StudentService;
import com.attendanceApp.services.impl.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentServiceImpl studentService;

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(studentDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/roll_no")
    public ResponseEntity<StudentProjection> getStudentByRollNo(@RequestParam String rollNo){
        return ResponseEntity.ok(studentService.getStudentByRollNo(rollNo));
    }
}
