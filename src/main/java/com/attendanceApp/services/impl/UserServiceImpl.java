package com.attendanceApp.services.impl;

import com.attendanceApp.dtos.*;
import com.attendanceApp.entities.Student;
import com.attendanceApp.entities.User;
import com.attendanceApp.enums.Role;
import com.attendanceApp.exceptions.DuplicateResourceException;
import com.attendanceApp.exceptions.ResourceNotFoundException;
import com.attendanceApp.repositories.UserRepository;
import com.attendanceApp.services.EmailService;
import com.attendanceApp.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    @Override
    @Cacheable(cacheNames = "Users", key = "#id")
    public UserDto getUserById(Long id) {
        log.info("User fetch with id: {}", id);
      User user = userRepo.findById(id)
              .orElseThrow(() -> new ResourceNotFoundException("User with id "+id+" not found"));

      return modelMapper.map(user, UserDto.class);
    }


    @Override
    public UserDto registerUser(UserDto dto) {
        if(userRepo.existsByUsername(dto.getUsername())){
            throw new DuplicateResourceException("Already exists");
        }

        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .fullName(dto.getFullName())
                .roles(Set.of(Role.TEACHER))
                .build();

        userRepo.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    @Transactional
    public StudentDTO registerStudent(RegisterRequestDto dto){
        if(userRepo.existsByUsername(dto.getUsername())){
            throw new DuplicateResourceException("Already exists");
        }

        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .fullName(dto.getFullName())
                .roles(Set.of(Role.STUDENT))
                .build();

        Student student = Student.builder()
                .fullName(dto.getFullName())
                .rollNo(dto.getRollNo())
                .batch(dto.getBatch())
                .build();

        student.setUser(user);
        userRepo.save(user);

        emailService.sendEmail(dto.getUsername());

        return modelMapper.map(student, StudentDTO.class);
    }


    @Override
    public UserDto updateExistingUser(Long id, UserDto updatedUser) {
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user with id "+id+" doesn't exists!"));

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());

        User newUpdatedUser = userRepo.save(existingUser);
        return modelMapper.map(newUpdatedUser, UserDto.class);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepo.findById(id)
                       .orElseThrow(() -> new ResourceNotFoundException("user with id "+id+" doesn't exists!"));
       userRepo.deleteById(id);
    }

    @Override
    public List<UserDto> getAllUsers(){
       return userRepo.findAllUsers()
               .stream().map(user -> modelMapper.map(user, UserDto.class))
               .collect(Collectors.toList());
    }

    public UserDto getUserByUserName(String name){
        User getUser = userRepo.findByUsername(name)
                .orElseThrow(() -> new ResourceNotFoundException(name+" doesn't exists!"));

        return modelMapper.map(getUser, UserDto.class);
    }
}