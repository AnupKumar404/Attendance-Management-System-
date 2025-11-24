package com.attendanceApp.dtos;

import jakarta.validation.constraints.NotBlank;

public class EmailSendDto {
    @NotBlank
    public String to;
    @NotBlank
    public String subject;
    @NotBlank
    public String body;
}
