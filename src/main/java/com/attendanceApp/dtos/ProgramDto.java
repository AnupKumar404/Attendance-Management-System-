package com.attendanceApp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record ProgramDto(

        Long departmentId,

        Integer duration,

        Integer totalSemesters
) {
}
