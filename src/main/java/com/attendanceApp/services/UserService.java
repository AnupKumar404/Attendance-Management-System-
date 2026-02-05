package com.attendanceApp.services;

import com.attendanceApp.dtos.*;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface UserService {

    UserDto registerUser(UserDto dto);

    StudentDTO registerStudent(RegisterRequestDto dto);

    UserDto getUserById(Long id);

    UserDto updateExistingUser(Long id, UserDto user);

    void deleteUserById(Long id);

    Page<UserDto> getAllUsers();

    UserDto updatePartial(Long id, Map<String, Object> updateValue);
}
