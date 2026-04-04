package com.attendanceApp.auth;

import com.attendanceApp.dtos.*;
import com.attendanceApp.utils.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    //Business logic of Login
    public JwtResponseDto login(LoginRequest req) throws AuthenticationException {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.email(), req.password())
            );

            UserPrincipal user = (UserPrincipal) authentication.getPrincipal();

            String token = jwtProvider.generateToken(user);

            log.info(token);

            return new JwtResponseDto(token);
        }
    }
