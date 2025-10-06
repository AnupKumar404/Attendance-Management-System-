package com.spring.attendanceApp.services.impl;

import com.spring.attendanceApp.dtos.UserDto;
import com.spring.attendanceApp.entities.User;
import com.spring.attendanceApp.enums.Role;
import com.spring.attendanceApp.exceptions.UserNotFoundException;
import com.spring.attendanceApp.repositories.UserRepository;
import com.spring.attendanceApp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

//    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto getUserById(Long id) {
      User user = userRepository.findById(id)
              .orElseThrow(() -> new UserNotFoundException("User not found with ID: "+id, null));

      return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto newUser) {
       User createdUser = modelMapper.map(newUser, User.class);
       User user = userRepository.save(createdUser);
       return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateExistingUser(Long id, UserDto updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User does not exists with ID: "+id, null));

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setRole(updatedUser.getRole());

        User newUpdatedUser = userRepository.save(existingUser);
        return modelMapper.map(newUpdatedUser, UserDto.class);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.findById(id)
                       .orElseThrow(() -> new UserNotFoundException("User not found with ID: "+id, null));
       userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers(){
       return userRepository.findAll();
    }

    @Override
    public User registerUser(String username, String password, String email, Role role) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
//        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        return userRepository.save(user);
    }

    public UserDto getUserByUserName(String name){
        User getUser = userRepository.findByUsername(name)
                .orElseThrow(() -> new UserNotFoundException("User not found", null));

        return modelMapper.map(getUser, UserDto.class);
    }
}