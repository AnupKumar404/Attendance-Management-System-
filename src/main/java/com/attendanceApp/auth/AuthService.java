package com.attendanceApp.auth;

import com.attendanceApp.dtos.*;
import com.attendanceApp.entities.Student;
import com.attendanceApp.entities.Teacher;
import com.attendanceApp.enums.Role;
import com.attendanceApp.exceptions.BadRequestException;
import com.attendanceApp.exceptions.DuplicateResourceException;
import com.attendanceApp.services.EmailService;
import com.attendanceApp.utils.JwtProvider;
import com.attendanceApp.entities.User;
import com.attendanceApp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    //Business logic of Login
    public JwtResponseDto login(LoginRequest req) throws AuthenticationException {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );

        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();

        String token = jwtProvider.generateToken(user);

        return new JwtResponseDto(token, user.getUsername(), user.getRoles().toString());
    }
}