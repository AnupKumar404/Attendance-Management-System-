package com.attendanceApp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DepartmentDto(

        @NotBlank
        @Size(max = 50)
        String name,

        @NotBlank
        String headOfDepartment
) {
        public DepartmentDto {
        }
}
