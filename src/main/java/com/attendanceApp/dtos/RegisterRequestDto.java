package com.attendanceApp.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    @NotBlank(message = "Username cannot be empty")
    @Email(message = "Invalid Email Format")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "must be length 8 or above")
    private String password;

    @NotBlank
    @Size(min = 3, max = 20, message = "must between 3-20")
    private String fullName;

    private String rollNo;

    private String batch;

}
