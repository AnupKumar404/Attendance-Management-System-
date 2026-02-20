package com.attendanceApp.controllers;

import com.attendanceApp.dtos.*;
import com.attendanceApp.projections.UserProjection;
import com.attendanceApp.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseUserDto> register(@Valid @RequestBody RequestUserDto req) {
        return new ResponseEntity<>(userService.registerUser(req), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDto> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping()
    public ResponseEntity<Page<ResponseUserDto>> getAll(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseUserDto> updateUser(@PathVariable Long id, @Valid @RequestBody RequestUserDto user){
        return new ResponseEntity<>(userService.updateExistingUser(id, user), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseUserDto> updateUserPartial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> dto){
        return new ResponseEntity<>(userService.updatePartial(id, dto), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<ResponseUserDto> getUserByName(@RequestParam String name){
        return ResponseEntity.ok(userService.getUserByName(name));
    }
}