package com.attendanceApp.services.impl;

import com.attendanceApp.dtos.RegisterTeacherRequestDto;
import com.attendanceApp.dtos.TeacherDTO;
import com.attendanceApp.dtos.TeacherResponseDto;
import com.attendanceApp.entities.Teacher;
import com.attendanceApp.entities.User;
import com.attendanceApp.enums.Role;
import com.attendanceApp.exceptions.DuplicateResourceException;
import com.attendanceApp.exceptions.ResourceNotFoundException;
import com.attendanceApp.repositories.TeacherRepository;
import com.attendanceApp.repositories.UserRepository;
import com.attendanceApp.services.TeacherService;
import com.attendanceApp.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TeacherServicesImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepo;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TeacherDTO createTeacher(TeacherDTO dto) {
        Teacher teacher = modelMapper.map(dto, Teacher.class);
        Teacher saved = teacherRepository.save(teacher);
        return modelMapper.map(saved, TeacherDTO.class);
    }

    @Transactional
    @Override
    public TeacherResponseDto registerTeacher(RegisterTeacherRequestDto dto){
        if(userRepo.existsByUsername(dto.getUsername())){
            throw new DuplicateResourceException("Already exists");
        }

        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .fullName(dto.getFullName())
                .roles(Set.of(Role.TEACHER))
                .build();

        Teacher teacher = Teacher.builder()
                .fullName(dto.getFullName())
                .department(dto.getDepartment())
                .build();

        userRepo.save(user);

        return modelMapper.map(user, TeacherResponseDto.class);
    }

    @Override
    public TeacherDTO getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        return modelMapper.map(teacher, TeacherDTO.class);
    }

    @Override
    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(teacher -> modelMapper.map(teacher, TeacherDTO.class)).toList();
    }
}