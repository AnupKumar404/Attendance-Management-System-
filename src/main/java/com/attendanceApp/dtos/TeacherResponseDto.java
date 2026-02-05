package com.attendanceApp.dtos;

import com.attendanceApp.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class TeacherResponseDto {

    private String fullName;

    private String department;
}
