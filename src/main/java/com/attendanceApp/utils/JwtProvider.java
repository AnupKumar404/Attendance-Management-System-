package com.attendanceApp.utils;

import com.attendanceApp.auth.UserPrincipal;
import com.attendanceApp.enums.UserRole;
import com.attendanceApp.exceptions.InvalidJwtException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration-ms}")
    private long jwtExpiration;

    // Digitally signed with secret key using cryptography
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    // Generate JWT
    public String generateToken(UserPrincipal user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("roles ", user.getRoles())
                .header().empty().add("typ", "JWT")
                .and()
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSigningKey())
                .compact();
    }

    // Validate JWT
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token);
            return true;
        } catch (InvalidJwtException e) {
            return false;
        }
    }

    public String extractUserRole(String token){
        return extractAllClaims(token).get("roles").toString();
    }

    // Extract all claims
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Extract username
    public String extractUsername(String token){
        return extractAllClaims(token).getSubject();
    }

    // Extract expiration time
    public Date extractExpiration(String token){
        return extractAllClaims(token).getExpiration();
    }

    // Check expiration of token
    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    // Validate the expiry of token
    public boolean validateExpiry(String token){
        return !isTokenExpired(token);
    }
}