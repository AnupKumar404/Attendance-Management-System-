package com.attendanceApp.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    @NotBlank
    @Email
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String fullName;

    private String rollNo;

    private String batch;

}
