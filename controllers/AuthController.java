package com.spring.attendanceApp.controllers;

import com.spring.attendanceApp.auth.AuthService;
import com.spring.attendanceApp.dtos.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@Valid @RequestBody LoginRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/register")
    public ResponseEntity<TeacherResponseDto> register(@Valid @RequestBody RegisterTeacherRequestDto req) {
        return ResponseEntity.ok(authService.registerTeacher(req));
    }

    @PostMapping("/register/student")
        public ResponseEntity<StudentDTO> registerStudent(@Valid @RequestBody RegisterRequestDto req){
            return ResponseEntity.ok(authService.registerStudent(req));
    }
}