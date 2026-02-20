package com.attendanceApp.services;

import com.attendanceApp.dtos.*;

import java.util.Map;

public interface UserService {

    ResponseUserDto registerUser(RequestUserDto dto);

    ResponseUserDto getUserById(Long id);

    ResponseUserDto updateExistingUser(Long id, RequestUserDto user);

    boolean changeStateOfUser(String name);

    ResponseUserDto updatePartial(Long id, Map<String, Object> updateValue);
}
