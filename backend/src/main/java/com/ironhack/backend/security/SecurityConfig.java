package com.ironhack.backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JpaUserDetailsService jpaUserDetailsService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/public").permitAll()
                .requestMatchers(HttpMethod.GET, "/user-management").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/user-management").permitAll()
                .requestMatchers(HttpMethod.PUT, "/user-management").hasRole("USER")
                .requestMatchers(HttpMethod.DELETE, "/user-management").hasRole("ADMIN")
                .anyRequest()
                .permitAll()
                .and()
                .userDetailsService(jpaUserDetailsService)
                .httpBasic()
                .and()
                .build();
    }
}
