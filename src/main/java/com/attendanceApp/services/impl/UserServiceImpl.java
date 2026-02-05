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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepo;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    private final EmailService emailService;


@Override
    public UserDto registerUser(UserDto dto) {
        if(userRepo.existsByUsername(dto.getUsername())){
            throw new DuplicateResourceException("Already exists");
        }

        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .fullName(dto.getFullName())
                .roles(Set.of(Role.ADMIN))
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
                .user(user)
                .rollNo(dto.getRollNo())
                .batch(dto.getBatch())
                .build();

        student.setUser(user);
        user.setStudent(student);
        userRepo.save(user);

        emailService.sendEmail(dto.getUsername(), dto.getFullName());

        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return modelMapper.map(user, UserDto.class);
    }


    @Override
    public UserDto updateExistingUser(Long id, UserDto updatedUser) {
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new
                        ResourceNotFoundException("user with id "+id+" doesn't exists!"));

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        existingUser.setFullName(updatedUser.getFullName());

        User newUpdatedUser = userRepo.save(existingUser);
        return modelMapper.map(newUpdatedUser, UserDto.class);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepo.findById(id)
                       .orElseThrow(() -> new
                               ResourceNotFoundException("user with id "+id+" doesn't exists!"));
       userRepo.deleteById(id);
    }

    @Override
    public Page<UserDto> getAllUsers(){
        Pageable pageable = PageRequest.of(0, 20);
        return userRepo.findAll(pageable)
                .map((element) -> modelMapper.map(element, UserDto.class));
    }

    @Override
    public UserDto updatePartial(Long id, Map<String, Object> updateValue) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        updateValue.forEach((key, value) -> {
            switch(key){
                case "username":
                    user.setUsername((String) value);
                    break;

                case "fullName":
                    user.setFullName((String) value);
                    break;

                case "password":
                    user.setPassword(passwordEncoder.encode((String) value));
                    break;

                default:
                    log.info("Invalid key..!");
            }
        });

        userRepo.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto getUserByName(String name){

        String finalName = Arrays.stream(name.split("\\s"))
                .map(word -> Character.toTitleCase(word.charAt(0))
                        + word.substring(1))
                .collect(Collectors.joining(" "));


        User getUser = userRepo.findByFullname(finalName)
                .orElseThrow(() -> new
                        ResourceNotFoundException(finalName+" doesn't exists!"));

        return modelMapper.map(getUser, UserDto.class);
    }
}