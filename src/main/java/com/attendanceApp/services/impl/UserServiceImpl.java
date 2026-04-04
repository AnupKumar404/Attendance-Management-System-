package com.attendanceApp.services.impl;

import com.attendanceApp.dtos.*;
import com.attendanceApp.entities.User;
import com.attendanceApp.enums.UserRole;
import com.attendanceApp.exceptions.DuplicateResourceException;
import com.attendanceApp.exceptions.ResourceNotFoundException;
import com.attendanceApp.projections.UserProjection;
import com.attendanceApp.repositories.StudentRepository;
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
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
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
    @Transactional
    public ResponseUserDto registerUser(RequestUserDto dto) {
        if(userRepo.existsByEmail(dto.email())){
            throw new DuplicateResourceException("Already exists");
        }

        User user = User.builder()
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .fullName(dto.fullName())
                .role(UserRole.ADMIN)
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userRepo.save(user);
        emailService.sendEmail(dto.email(), dto.fullName());
        return modelMapper.map(user, ResponseUserDto.class);
    }


    @Override
    public ResponseUserDto getUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return modelMapper.map(user, ResponseUserDto.class);
    }


    @Override
    public ResponseUserDto updateExistingUser(Long id, RequestUserDto updatedUser) {
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new
                        ResourceNotFoundException("user with id "+id+" doesn't exists!"));

        existingUser.setEmail(updatedUser.email());
        existingUser.setPassword(passwordEncoder.encode(updatedUser.password()));
        existingUser.setFullName(updatedUser.fullName());

        User newUpdatedUser = userRepo.save(existingUser);
        return modelMapper.map(newUpdatedUser, ResponseUserDto.class);
    }

    @Override
    public Page<ResponseUserDto> getAllUsers(@PageableDefault(page = 0, size = 20, direction = Sort.Direction.DESC)
                                             Pageable pageable){

        return userRepo.findAllUsers(pageable).map(user -> modelMapper.map(user, ResponseUserDto.class));
    }

    @Override
    public ResponseUserDto updatePartial(Long id, Map<String, Object> updateValue) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        updateValue.forEach((key, value) -> {
            switch(key){
                case "email":
                    user.setEmail((String) value);
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
        return modelMapper.map(user, ResponseUserDto.class);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userRepo.delete(user);
    }

    @Override
    public ResponseUserDto getUserByName(String fullName){

        String finalName = Arrays.stream(fullName.split("\\s"))
                .map(word -> Character.toTitleCase(word.charAt(0))
                        + word.substring(1))
                .collect(Collectors.joining(" "));


        UserProjection user = userRepo.findByFullName(finalName)
                .orElseThrow(() -> new
                        ResourceNotFoundException(finalName+" doesn't exists"));

        return modelMapper.map(user, ResponseUserDto.class);
    }
}