package com.attendanceApp.controllers;

import com.attendanceApp.dtos.AttendanceRecordDTO;
import com.attendanceApp.services.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

//    @PostMapping
//    public ResponseEntity<AttendanceRecordDTO> addAttendance
//            (@PathVariable Long id, @RequestBody AttendanceRecordDTO dtoList){
//        return new ResponseEntity<>(attendanceService.markAttendance(id,dtoList, HttpStatus.CREATED);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<List<AttendanceRecordDTO>> getSpecificRecord(@PathVariable Long id){
        return ResponseEntity.ok(attendanceService.getAttendanceBySession(id));
    }
}
