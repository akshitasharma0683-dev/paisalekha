package com.akshita.paisalekha.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // Disable CSRF for REST APIs
            .csrf(csrf -> csrf.disable())

            // Authorization rules
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/api/users/register",
                    "/api/users/**",
                    "/actuator/health"
                ).permitAll()
                .anyRequest().authenticated()
            )

            // Disable default login form
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}