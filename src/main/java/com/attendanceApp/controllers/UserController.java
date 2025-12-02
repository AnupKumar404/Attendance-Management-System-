package com.attendanceApp.controllers;

import com.attendanceApp.dtos.*;
import com.attendanceApp.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserDto req) {
        return new ResponseEntity<>(userService.registerUser(req), HttpStatus.CREATED);
    }

    @PostMapping("/register/student")
    public ResponseEntity<StudentDTO> registerStudent(@Valid @RequestBody RegisterRequestDto req){
        return new ResponseEntity<>(userService.registerStudent(req), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/get")
    public ResponseEntity<List<UserDto>> getAll(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }


    @PutMapping("/modify/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id,@Valid @RequestBody UserDto user){
        return new ResponseEntity<>(userService.updateExistingUser(id, user), HttpStatus.OK);
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<UserDto> updateUserPartial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> dto){
        return new ResponseEntity<>(userService.updatePartial(id, dto), HttpStatus.OK);
    }


    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getBy")
    public ResponseEntity<UserDto> getUserByUsername(@RequestParam String username){
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }
}