package com.attendanceApp.dtos;


public record JwtResponseDto(
        String token,
        String role
) {

}
