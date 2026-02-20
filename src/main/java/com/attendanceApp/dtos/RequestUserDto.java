package com.attendanceApp.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record RequestUserDto(

        @NotBlank
        @Email(message = "Invalid Email Format")
        String email,

        @NotBlank
        @Size(min = 8, message = "must be length 8 or above")
        String password,

        @NotBlank
        @Size(min = 3, max = 20, message = "must between 3-20")
        String fullName
)
{
}