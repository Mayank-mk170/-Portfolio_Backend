package com.portfolio.controller;

import com.portfolio.dto.ErrorResponse;
import com.portfolio.dto.LoginRequest;
import com.portfolio.dto.LoginResponse;
import com.portfolio.dto.RegisterRequest;
import com.portfolio.service.AuthService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody RegisterRequest request
    ) {

        try {

            authService.register(request);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(
                            new ErrorResponse(
                                    "Admin registered successfully"
                            )
                    );

        } catch (IllegalArgumentException e) {

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(
                            new ErrorResponse(
                                    e.getMessage()
                            )
                    );
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {

        return ResponseEntity.ok(
                authService.login(request)
        );
    }
}