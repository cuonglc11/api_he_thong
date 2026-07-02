package com.tracking.api_tracking.config;

import com.tracking.api_tracking.constants.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationFilter jwtFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. TẮT CSRF (Bắt buộc đối với API)
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        // 2. Mở khóa cho API đăng nhập
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/admin/**").hasRole(AppConstants.ADMIN)
                        .requestMatchers("/error").permitAll()

                        .anyRequest().authenticated()
                );

        // (Nếu bạn có thêm Filter JWT thì cứ giữ nguyên đoạn .addFilterBefore ở dưới nhé)
       http.addFilterBefore(jwtFilter , UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}