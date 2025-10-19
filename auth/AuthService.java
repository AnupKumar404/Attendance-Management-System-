package com.spring.attendanceApp.auth;

import com.spring.attendanceApp.dtos.*;
import com.spring.attendanceApp.entities.Teacher;
import com.spring.attendanceApp.enums.Role;
import com.spring.attendanceApp.repositories.TeacherRepository;
import com.spring.attendanceApp.security.JwtProvider;
import com.spring.attendanceApp.entities.User;
import com.spring.attendanceApp.repositories.UserRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService userDetailsService;
    private final ModelMapper modelMapper;
    private final TeacherRepository teacherRepository;

    public JwtResponseDto login(LoginRequest req) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userPrincipal.getUser();

        String token = jwtProvider.generateToken(user);


        return new JwtResponseDto(token, user.getId(), user.getUsername());
    }

//    public RegisterResponseDto register(RegisterRequestDto req) {
//
//    }

    public TeacherResponseDto registerTeacher(RegisterTeacherRequestDto dto){
        if(userRepo.existsByUsername(dto.getUsername())){
            throw new EntityExistsException("Already exists");
        }

        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .fullName(dto.getFullName())
                .roles(Set.of(Role.TEACHER))
                .build();

        userRepo.save(user);

        Teacher teacher = Teacher.builder()
                .user(user)
                .fullName(dto.getFullName())
                .department(dto.getDepartment())
                .build();

        teacherRepository.save(teacher);

       return modelMapper.map(user, TeacherResponseDto.class);
    }
}