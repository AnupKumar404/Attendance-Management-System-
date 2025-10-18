package com.spring.attendanceApp.auth;

import com.spring.attendanceApp.dtos.RegisterResponseDto;
import com.spring.attendanceApp.enums.Role;
import com.spring.attendanceApp.security.JwtProvider;
import com.spring.attendanceApp.dtos.JwtResponseDto;
import com.spring.attendanceApp.dtos.LoginRequest;
import com.spring.attendanceApp.dtos.RegisterRequestDto;
import com.spring.attendanceApp.entities.User;
import com.spring.attendanceApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService userDetailsService;
    private final ModelMapper modelMapper;

    public JwtResponseDto login(LoginRequest req) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userPrincipal.getUser();

        String token = jwtProvider.generateToken(user);


        return new JwtResponseDto(token, user.getId(), user.getUsername());
    }

    public RegisterResponseDto register(RegisterRequestDto req) {
        if (userRepo.existsByUsername(req.getUsername())) {
            throw new UsernameNotFoundException("User Not found");
        }


       User user = User.builder()
                .username(req.getUsername())
                .password(passwordEncoder.encode(req.getPassword()))
                .fullName(req.getFullName())
                .build();

        user.getRoles().add(Role.TEACHER);
        User saved = userRepo.save(user);
        return modelMapper.map(saved, RegisterResponseDto.class);

//        JwtResponseDto resp = new JwtResponseDto();
//        resp.setToken(jwtProvider.generateToken(saved));
//        resp.setUserId(saved.getId());
//        resp.setUsername(saved.getUsername());
//        return resp;
    }
}