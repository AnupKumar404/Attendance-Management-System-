package com.spring.attendanceApp.dtos;

import com.spring.attendanceApp.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "Name is required")
    private String username;

    @Email
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}