package com.spring.attendanceApp.controllers;

import com.spring.attendanceApp.dtos.AttendanceReqDto;
import com.spring.attendanceApp.dtos.AttendanceResDto;
import com.spring.attendanceApp.services.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private AttendanceService attendanceService;


}
