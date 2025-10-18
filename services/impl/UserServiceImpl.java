package com.spring.attendanceApp.services.impl;

import com.spring.attendanceApp.dtos.UserDto;
import com.spring.attendanceApp.entities.User;
import com.spring.attendanceApp.repositories.UserRepository;
import com.spring.attendanceApp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public UserDto getUserById(Long id) {
      User user = userRepository.findById(id)
              .orElseThrow();

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
                .orElseThrow();

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());

        User newUpdatedUser = userRepository.save(existingUser);
        return modelMapper.map(newUpdatedUser, UserDto.class);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.findById(id)
                       .orElseThrow();
       userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getAllUsers(){
       return userRepository.findAll()
               .stream().map(user -> modelMapper.map(user, UserDto.class))
               .collect(Collectors.toList());
    }

    public UserDto getUserByUserName(String name){
        User getUser = userRepository.findByUsername(name)
                .orElseThrow();

        return modelMapper.map(getUser, UserDto.class);
    }
}