package com.spring.attendanceApp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponseDto {
    private String token;
//    private String tokenType = "Bearer";
    private Long userId;
    private String username;
}
