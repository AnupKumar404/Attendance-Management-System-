package com.attendanceApp.services;

import com.attendanceApp.dtos.*;

import java.util.List;

public interface UserService {

    UserDto registerUser(UserDto dto);

    StudentDTO registerStudent(RegisterRequestDto dto);

    UserDto getUserById(Long id);

    UserDto updateExistingUser(Long id, UserDto user);

    void deleteUserById(Long id);

    List<UserDto> getAllUsers();
}
