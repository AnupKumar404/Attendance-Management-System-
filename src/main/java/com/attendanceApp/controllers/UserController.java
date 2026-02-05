package com.attendanceApp.controllers;

import com.attendanceApp.dtos.*;
import com.attendanceApp.entities.User;
import com.attendanceApp.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
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

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping()
    public ResponseEntity<Page<UserDto>> getAll(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id,@Valid @RequestBody UserDto user){
        return new ResponseEntity<>(userService.updateExistingUser(id, user), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updateUserPartial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> dto){
        return new ResponseEntity<>(userService.updatePartial(id, dto), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user")
    public ResponseEntity<UserDto> getUserByName(@RequestParam String name){
        return ResponseEntity.ok(userService.getUserByName(name));
    }
}