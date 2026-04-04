package com.attendanceApp.controllers;

import com.attendanceApp.dtos.DepartmentDto;
import com.attendanceApp.dtos.ProgramDto;
import com.attendanceApp.services.DepartmentService;
import com.attendanceApp.services.ProgramService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final DepartmentService departmentService;
    private final ProgramService programService;

    @PostMapping("/department")
    public ResponseEntity<DepartmentDto> createProgram(@Valid @RequestBody DepartmentDto dto){
        return new ResponseEntity<>(departmentService.addDepartment(dto), HttpStatus.CREATED);
    }

    @PostMapping("/program")
    public ResponseEntity<ProgramDto> createProgram(@Valid @RequestBody ProgramDto dto){
        return new ResponseEntity<>(programService.createProgram(dto), HttpStatus.CREATED);
    }
}
