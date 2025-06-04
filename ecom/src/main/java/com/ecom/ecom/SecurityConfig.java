package com.ecom.ecom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder encoder) {
        var user = User.withUsername("user")
                .password(encoder.encode("user123"))
                .roles("USER")
                .build();

        var admin = User.withUsername("admin")
                .password(encoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/products/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/products", "/api/products/search/**", "/api/products/filter/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/products/{id}", "/api/products/{id}/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/products", "/api/products/**").hasRole("ADMIN") // POST/PUT/DELETE
                        .anyRequest().authenticated()
                )
                .httpBasic()
                .and()
                .build();
    }
}
