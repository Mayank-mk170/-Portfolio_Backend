package com.portfolio.config;

import com.portfolio.security.JwtFilter;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .cors(cors -> {})

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth

                        // ========================================
                        // AUTH
                        // ========================================

                        .requestMatchers(
                                "/api/auth/register",
                                "/api/auth/login"
                        ).permitAll()


                        // ========================================
                        // CONTACT - PUBLIC SUBMIT
                        // ========================================

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/contact"
                        ).permitAll()


                        // ========================================
                        // CONTACT - ADMIN READ
                        // ========================================

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/contact",
                                "/api/contact/**"
                        ).hasRole("ADMIN")


                        // ========================================
                        // CONTACT - ADMIN DELETE
                        // ========================================

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/contact/**"
                        ).hasRole("ADMIN")


                        // ========================================
                        // PUBLIC GET
                        // ========================================

                        .requestMatchers(
                                HttpMethod.GET,

                                "/api/about",

                                "/api/skills",
                                "/api/skills/**",

                                "/api/projects",
                                "/api/projects/**",

                                "/api/blogs",
                                "/api/blogs/**",

                                "/api/experience",
                                "/api/experience/**",

                                "/api/testimonials",
                                "/api/testimonials/**",

                                "/api/services",
                                "/api/services/**",

                                "/api/user-profile"
                        ).permitAll()


                        // ========================================
                        // ADMIN - CREATE
                        // ========================================

                        .requestMatchers(
                                HttpMethod.POST,

                                "/api/about",
                                "/api/skills",
                                "/api/projects",
                                "/api/blogs",
                                "/api/experience",
                                "/api/testimonials",
                                "/api/services",
                                "/api/uploads",
                                "/api/user-profile"
                        ).hasRole("ADMIN")


                        // ========================================
                        // ADMIN - UPDATE
                        // ========================================

                        .requestMatchers(
                                HttpMethod.PUT,

                                "/api/about/**",
                                "/api/skills/**",
                                "/api/projects/**",
                                "/api/blogs/**",
                                "/api/experience/**",
                                "/api/testimonials/**",
                                "/api/services/**"
                        ).hasRole("ADMIN")


                        // ========================================
                        // ADMIN - DELETE
                        // ========================================

                        .requestMatchers(
                                HttpMethod.DELETE,

                                "/api/about/**",
                                "/api/skills/**",
                                "/api/projects/**",
                                "/api/blogs/**",
                                "/api/experience/**",
                                "/api/testimonials/**",
                                "/api/services/**"
                        ).hasRole("ADMIN")


                        // ========================================
                        // EVERYTHING ELSE
                        // ========================================

                        .anyRequest().authenticated()
                )

                // ========================================
                // JWT FILTER
                // ========================================

                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}