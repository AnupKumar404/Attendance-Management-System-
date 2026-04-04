package com.attendanceApp.services;

import com.attendanceApp.dtos.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface UserService {

    ResponseUserDto registerUser(RequestUserDto dto);

    ResponseUserDto getUserById(Long id);

    ResponseUserDto updateExistingUser(Long id, RequestUserDto user);

    ResponseUserDto getUserByName(String name);

    Page<ResponseUserDto> getAllUsers(Pageable pageable);

    ResponseUserDto updatePartial(Long id, Map<String, Object> updateValue);

    void deleteUserById(Long id);
}
