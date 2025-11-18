package com.attendanceApp.controllers;

import com.attendanceApp.dtos.AttendanceSessionDTO;
import com.attendanceApp.services.AttendanceSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendanceSession")
@RequiredArgsConstructor
public class AttendanceSessionController {

    private final AttendanceSessionService attendanceSessionService;

    @PostMapping("/subject/{subjectId}")
    public ResponseEntity<AttendanceSessionDTO> createSession(@PathVariable Long subjectId,
                                                              @RequestBody AttendanceSessionDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(attendanceSessionService.createSession(subjectId, dto));
    }

    @GetMapping("get/{id}")
    public ResponseEntity<AttendanceSessionDTO> getSession(@PathVariable Long id){
        return ResponseEntity.ok(attendanceSessionService.getSessionById(id));
    }

    @GetMapping
    public ResponseEntity<List<AttendanceSessionDTO>> getAllSessions(){
        return ResponseEntity.ok(attendanceSessionService.getAllSessions());
    }

    @PutMapping("update/{id}")
    public ResponseEntity<AttendanceSessionDTO> updateSession
            (@PathVariable Long id, @RequestBody AttendanceSessionDTO dto){

        return ResponseEntity.ok(attendanceSessionService.updateSession(id, dto));
    }

}