package com.example.quiz.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    // Define the security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for Postman/testing
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**", "/h2-console/**").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults()); // Enable Basic Auth

        // For H2 console if needed
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }

    // In-memory user details
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User
            .withUsername("admin")
            .password("admin123")
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(admin);
    }

    // NoOp password encoder (not for production!)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // for plaintext passwords
    }
}
