package com.spring.attendanceApp.services;

import com.spring.attendanceApp.dtos.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);

    UserDto getUserById(Long id);

    UserDto updateExistingUser(Long id, UserDto user);

    void deleteUserById(Long id);

    List<UserDto> getAllUsers();
}
