package com.portfolio.service;

import com.portfolio.dto.LoginRequest;
import com.portfolio.dto.LoginResponse;
import com.portfolio.dto.RegisterRequest;
import com.portfolio.entity.User;
import com.portfolio.repository.UserRepository;
import com.portfolio.security.JwtService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException(
                    "Email is already registered"
            );
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )
                .role("ADMIN")
                .enabled(true)
                .build();

        userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new BadCredentialsException(
                                "Invalid email or password"
                        )
                );

        if (!Boolean.TRUE.equals(user.getEnabled())) {
            throw new BadCredentialsException(
                    "User account is disabled"
            );
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new BadCredentialsException(
                    "Invalid email or password"
            );
        }

        String token = jwtService.generateToken(
                user.getEmail()
        );

        return new LoginResponse(
                token,
                "Bearer"
        );
    }
}