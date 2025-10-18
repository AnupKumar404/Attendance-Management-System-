package com.spring.attendanceApp.controllers;

import com.spring.attendanceApp.auth.AuthService;
import com.spring.attendanceApp.dtos.JwtResponseDto;
import com.spring.attendanceApp.dtos.LoginRequest;
import com.spring.attendanceApp.dtos.RegisterRequestDto;
import com.spring.attendanceApp.dtos.RegisterResponseDto;
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
    public ResponseEntity<RegisterResponseDto> register(@Valid @RequestBody RegisterRequestDto req) {
        return ResponseEntity.ok(authService.register(req));
    }
}
