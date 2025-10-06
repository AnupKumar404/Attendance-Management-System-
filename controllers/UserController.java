package com.spring.attendanceApp.controllers;

import com.spring.attendanceApp.dtos.UserDto;
import com.spring.attendanceApp.entities.User;
import com.spring.attendanceApp.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<UserDto> createNewUser(@Valid @RequestBody UserDto user){
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/get{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/get")
    public ResponseEntity<List<User>> getAll(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id,@Valid @RequestBody UserDto user){
        return new ResponseEntity<>(userService.updateExistingUser(id, user), HttpStatus.OK);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getBy")
    public ResponseEntity<UserDto> getUserByUserName(@RequestParam String username){
        return ResponseEntity.ok(userService.getUserByUserName(username));
    }
}