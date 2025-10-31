package com.spring.attendanceApp.dtos;

import com.spring.attendanceApp.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class TeacherResponseDto {
    private String username;

    private String fullName;

    private Set<Role> roles;
}
