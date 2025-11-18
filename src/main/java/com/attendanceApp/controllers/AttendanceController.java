package com.attendanceApp.controllers;

import com.attendanceApp.dtos.AttendanceRecordDTO;
import com.attendanceApp.services.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

//    @PostMapping
//    public ResponseEntity<List<AttendanceRecordDTO>> addAttendance
//            (@RequestBody List<AttendanceRecordDTO> dtoList){
//        return new ResponseEntity<>(attendanceService.markAttendance((dtoList.getFirst().getSessionId(), dtoList.getLast().getStudentId()), HttpStatus.CREATED);
//    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<AttendanceRecordDTO>> getSpecificRecord(@PathVariable Long id){
        return ResponseEntity.ok(attendanceService.getAttendanceBySession(id));
    }
}
