package com.spring.attendanceApp.auth;

import com.spring.attendanceApp.dtos.*;
import com.spring.attendanceApp.entities.Student;
import com.spring.attendanceApp.entities.Teacher;
import com.spring.attendanceApp.enums.Role;
import com.spring.attendanceApp.repositories.StudentRepository;
import com.spring.attendanceApp.repositories.TeacherRepository;
import com.spring.attendanceApp.utils.JwtProvider;
import com.spring.attendanceApp.entities.User;
import com.spring.attendanceApp.repositories.UserRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final TeacherRepository teacherRepository;

    public JwtResponseDto login(LoginRequest req) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userPrincipal.getUser();

        String token = jwtProvider.generateToken(user);

        return new JwtResponseDto(token, user.getUsername());
    }

//    public void register() {
//        if()
//    }

    public TeacherResponseDto registerTeacher(RegisterTeacherRequestDto dto){
        if(userRepo.existsByUsername(dto.getUsername())){
//            throw new EntityExistsException("Already exists");
        }

        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .fullName(dto.getFullName())
                .roles(Set.of(Role.TEACHER))
                .build();

        Teacher teacher = Teacher.builder()
                .user(user)
                .fullName(dto.getFullName())
                .department(dto.getDepartment())
                .build();

        user.setTeacher(teacher);
        userRepo.save(user);

       return modelMapper.map(user, TeacherResponseDto.class);
    }

    public StudentDTO registerStudent(RegisterRequestDto dto){
        if(userRepo.existsByUsername(dto.getUsername())){
//            throw new UsernameNotFoundException();
        }

        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(Set.of(Role.STUDENT))
                .build();

        Student student = Student.builder()
                .user(user)
                .fullName(dto.getFullName())
                .rollNo(dto.getRollNo())
                .batch(dto.getBatch())
                .build();

        user.setStudent(student);
        userRepo.save(user);

        return modelMapper.map(user, StudentDTO.class);
    }
}