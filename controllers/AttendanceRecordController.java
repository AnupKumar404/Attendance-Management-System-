package com.spring.attendanceApp.controllers;

import com.spring.attendanceApp.entities.AttendanceRecord;
import com.spring.attendanceApp.enums.AttendanceStatus;
import com.spring.attendanceApp.services.impl.AttendanceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("records")
@RequiredArgsConstructor
public class AttendanceRecordController {

    private final AttendanceServiceImpl attendanceRecordService;

    @GetMapping("/get/{id}")
    public ResponseEntity<List<AttendanceRecord>> getAttendanceRecord(@PathVariable("id") Long id){
        return ResponseEntity.ok(attendanceRecordService.getAttendanceByStudent(id));
    }

    @PostMapping("/{studentId}/{sessionId}")
    public ResponseEntity<AttendanceRecord> markAttendance(@PathVariable Long studentId,
                                                           @PathVariable Long sessionId, @RequestParam AttendanceStatus status){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(attendanceRecordService.markAttendance(studentId, sessionId, status));
    }
}
