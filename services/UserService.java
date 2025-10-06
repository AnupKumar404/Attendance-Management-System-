package com.spring.attendanceApp.services;

import com.spring.attendanceApp.dtos.UserDto;
import com.spring.attendanceApp.entities.User;
import com.spring.attendanceApp.enums.Role;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);

    UserDto getUserById(Long id);

    UserDto updateExistingUser(Long id, UserDto user);

    void deleteUserById(Long id);

    List<User> getAllUsers();

    User registerUser(String username, String password, String email, Role role);
}
