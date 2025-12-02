package com.attendanceApp.auth;

import com.attendanceApp.dtos.*;
import com.attendanceApp.utils.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;


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