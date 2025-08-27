package com.backendguru.mvc_demo.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf( c -> c.disable())
            .authorizeHttpRequests(
                a -> a
                        .requestMatchers("/actuator/**").permitAll()
                        .anyRequest().authenticated()
            )
                .httpBasic( b -> {})

        ;
        return http.build();
    }
}
