package com.spring.attendanceApp.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AttendanceSessionDTO {
    private Long id;
    private LocalDate sessionDate;
    private Long subjectId;
    private List<Long> recordIds;
}
