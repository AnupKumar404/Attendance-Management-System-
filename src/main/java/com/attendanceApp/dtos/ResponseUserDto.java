package com.attendanceApp.dtos;

import com.attendanceApp.enums.UserRole;
import lombok.Data;

@Data
public class ResponseUserDto{

        Long id;

        String email;

        String fullName;

        UserRole role;

        Boolean isActive;

}
