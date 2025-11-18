package com.attendanceApp.dtos;

import com.attendanceApp.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class TeacherResponseDto {
    private String username;

    private String fullName;

    private Set<Role> roles;
}
