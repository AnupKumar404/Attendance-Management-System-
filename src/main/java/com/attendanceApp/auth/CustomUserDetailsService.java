package com.attendanceApp.auth;

import com.attendanceApp.entities.User;
import com.attendanceApp.exceptions.ResourceNotFoundException;
import com.attendanceApp.projections.LoginProjection;
import com.attendanceApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email){
        LoginProjection user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + email));

        return new UserPrincipal(user);
    }
}