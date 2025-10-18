package com.spring.attendanceApp.dtos;

import com.spring.attendanceApp.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponseDto {
    private String username;

    private String password;

    private String fullName;

    private Set<Role> roles;
}
