package com.portfolio.security;

import com.portfolio.entity.User;
import com.portfolio.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {


        // ==========================================
        // AUTH ENDPOINTS
        // ==========================================

        String path = request.getServletPath();

        if (
                path.equals("/api/auth/register")
                        || path.equals("/api/auth/login")
        ) {

            filterChain.doFilter(
                    request,
                    response
            );

            return;
        }


        // ==========================================
        // GET AUTHORIZATION HEADER
        // ==========================================

        String authorizationHeader =
                request.getHeader("Authorization");


        // ==========================================
        // NO JWT
        // ==========================================

        if (
                authorizationHeader == null
                        || !authorizationHeader.startsWith("Bearer ")
        ) {

            filterChain.doFilter(
                    request,
                    response
            );

            return;
        }


        // ==========================================
        // EXTRACT TOKEN
        // ==========================================

        String token =
                authorizationHeader.substring(7);


        try {


            // ==========================================
            // VALIDATE TOKEN
            // ==========================================

            if (!jwtService.isTokenValid(token)) {

                System.out.println(
                        "JWT INVALID"
                );

                filterChain.doFilter(
                        request,
                        response
                );

                return;
            }


            // ==========================================
            // GET EMAIL
            // ==========================================

            String email =
                    jwtService.extractEmail(token);


            if (email == null || email.isBlank()) {

                System.out.println(
                        "JWT EMAIL IS EMPTY"
                );

                filterChain.doFilter(
                        request,
                        response
                );

                return;
            }


            // ==========================================
            // FIND USER
            // ==========================================

            User user =
                    userRepository
                            .findByEmail(email)
                            .orElse(null);


            if (user == null) {

                System.out.println(
                        "USER NOT FOUND: " + email
                );

                filterChain.doFilter(
                        request,
                        response
                );

                return;
            }


            // ==========================================
            // CHECK ENABLED
            // ==========================================

            if (!Boolean.TRUE.equals(
                    user.getEnabled()
            )) {

                System.out.println(
                        "USER DISABLED: " + email
                );

                filterChain.doFilter(
                        request,
                        response
                );

                return;
            }


            // ==========================================
            // DON'T OVERRIDE EXISTING AUTHENTICATION
            // ==========================================

            if (
                    SecurityContextHolder
                            .getContext()
                            .getAuthentication()
                            != null
            ) {

                filterChain.doFilter(
                        request,
                        response
                );

                return;
            }


            // ==========================================
            // GET USER ROLE
            // ==========================================

            String role =
                    user.getRole();


            if (
                    role == null
                            || role.trim().isEmpty()
            ) {

                System.out.println(
                        "USER HAS NO ROLE: " + email
                );

                filterChain.doFilter(
                        request,
                        response
                );

                return;
            }


            // ==========================================
            // NORMALIZE ROLE
            // ==========================================

            role = role
                    .trim()
                    .toUpperCase();


            if (!role.startsWith("ROLE_")) {

                role = "ROLE_" + role;

            }


            // ==========================================
            // DEBUG
            // ==========================================

            System.out.println(
                    "================================="
            );

            System.out.println(
                    "JWT USER  : " + email
            );

            System.out.println(
                    "DB ROLE   : " + user.getRole()
            );

            System.out.println(
                    "AUTHORITY : " + role
            );

            System.out.println(
                    "REQUEST   : "
                            + request.getMethod()
                            + " "
                            + request.getRequestURI()
            );

            System.out.println(
                    "================================="
            );


            // ==========================================
            // CREATE AUTHENTICATION
            // ==========================================

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            null,
                            List.of(
                                    new SimpleGrantedAuthority(
                                            role
                                    )
                            )
                    );


            // ==========================================
            // SET SECURITY CONTEXT
            // ==========================================

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(
                            authentication
                    );


        } catch (Exception e) {

            System.out.println(
                    "JWT ERROR: "
                            + e.getMessage()
            );

        }


        // ==========================================
        // CONTINUE REQUEST
        // ==========================================

        filterChain.doFilter(
                request,
                response
        );
    }
}