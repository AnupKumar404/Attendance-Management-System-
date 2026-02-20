package com.attendanceApp.services.impl;

import com.attendanceApp.dtos.*;
import com.attendanceApp.entities.Students;
import com.attendanceApp.entities.Users;
import com.attendanceApp.enums.UserRole;
import com.attendanceApp.exceptions.DuplicateResourceException;
import com.attendanceApp.exceptions.ResourceNotFoundException;
import com.attendanceApp.projections.UserProjection;
import com.attendanceApp.repositories.StudentsRepository;
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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepo;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    private final EmailService emailService;

    private final StudentsRepository studentsRepo;

    private Long roll = 2443045l;


    @Override
    @Transactional
    public ResponseUserDto registerUser(RequestUserDto dto) {
        if(userRepo.existsByEmail(dto.email())){
            throw new DuplicateResourceException("Already exists");
        }

        Users user = Users.builder()
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .fullName(dto.fullName())
                .role(UserRole.ROLE_STUDENT)
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .build();

        Students student = Students.builder()
                .user(user)
                .rollNumber((roll += 1).toString())
                .fatherName("Anil Mali")
                .build();

        userRepo.save(user);
        studentsRepo.save(student);
        emailService.sendEmail(dto.email(), dto.fullName());
        return modelMapper.map(user, ResponseUserDto.class);
    }


    @Override
    public ResponseUserDto getUserById(Long id) {
        Users user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return modelMapper.map(user, ResponseUserDto.class);
    }


    @Override
    public ResponseUserDto updateExistingUser(Long id, RequestUserDto updatedUser) {
        Users existingUsers = userRepo.findById(id)
                .orElseThrow(() -> new
                        ResourceNotFoundException("user with id "+id+" doesn't exists!"));

        existingUsers.setEmail(updatedUser.email());
        existingUsers.setPassword(passwordEncoder.encode(updatedUser.password()));
        existingUsers.setFullName(updatedUser.fullName());

        Users newUpdatedUsers = userRepo.save(existingUsers);
        return modelMapper.map(newUpdatedUsers, ResponseUserDto.class);
    }

    @Override
    public boolean changeStateOfUser(String name) {
        return userRepo.isUserActive(name)
                 .orElseThrow(() -> new ResourceNotFoundException("User not Found"));
    }


    public Page<ResponseUserDto> getAllUsers(){
        Pageable pageable = PageRequest.of(0, 20);
        return userRepo.findAllUsers(pageable)
                .map((element) -> modelMapper.map(element, ResponseUserDto.class));
    }

    @Override
    public ResponseUserDto updatePartial(Long id, Map<String, Object> updateValue) {
        Users users = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        updateValue.forEach((key, value) -> {
            switch(key){
                case "username":
                    users.setEmail((String) value);
                    break;

                case "fullName":
                    users.setFullName((String) value);
                    break;

                case "password":
                    users.setPassword(passwordEncoder.encode((String) value));
                    break;

                default:
                    log.info("Invalid key..!");
            }
        });

        userRepo.save(users);
        return modelMapper.map(users, ResponseUserDto.class);
    }

    public ResponseUserDto getUserByName(String name){

        String finalName = Arrays.stream(name.split("\\s"))
                .map(word -> Character.toTitleCase(word.charAt(0))
                        + word.substring(1))
                .collect(Collectors.joining(" "));


        UserProjection getUsers = userRepo.findByFullName(finalName)
                .orElseThrow(() -> new
                        ResourceNotFoundException(finalName+" doesn't exists!"));

        return modelMapper.map(getUsers, ResponseUserDto.class);
    }
}