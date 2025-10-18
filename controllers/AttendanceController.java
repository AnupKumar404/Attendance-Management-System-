package com.spring.attendanceApp.controllers;

import com.spring.attendanceApp.dtos.AttendanceRecordDTO;
import com.spring.attendanceApp.services.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<List<AttendanceRecordDTO>> addAttendance
            (@PathVariable Long sessionId, List<AttendanceRecordDTO> dtoList){
        return new ResponseEntity<>(attendanceService.markAttendance(sessionId, dtoList), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<AttendanceRecordDTO>> getSpecificRecord(@PathVariable Long id){
        return ResponseEntity.ok(attendanceService.getAttendanceBySession(id));
    }
}
