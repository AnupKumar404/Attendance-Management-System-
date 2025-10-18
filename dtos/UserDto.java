package com.spring.attendanceApp.dtos;

import com.spring.attendanceApp.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {

    @NotBlank(message = "Name is required")
    private String username;

    private String password;

    private String fullName;

    @Enumerated(EnumType.STRING)
    private Role role;
}