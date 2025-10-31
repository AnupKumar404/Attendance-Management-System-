package com.spring.attendanceApp.dtos;

import com.spring.attendanceApp.enums.Role;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {

    private String username;

    private String password;

    private String fullName;

    private Set<Role> roles;
}