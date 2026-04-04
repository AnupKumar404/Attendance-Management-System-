package com.attendanceApp.controllers;

import com.attendanceApp.auth.AuthService;
import com.attendanceApp.dtos.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest req) {

        JwtResponseDto result = authService.login(req);

            ResponseCookie cookie = ResponseCookie.from("access_token", result.token())
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(10 * 60)
                    .sameSite("Strict")
                    .build();

            String message = "Login Successfully";

            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(message);
    }
}