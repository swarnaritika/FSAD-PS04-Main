package com.givehope.backend.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) -> writeJson(response, 401,
                                Map.of("message", "Not authorized, no token")))
                        .accessDeniedHandler((request, response, accessDeniedException) -> writeJson(response, 401,
                                Map.of("message", "Not authorized as an admin"))))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/").permitAll()
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/donations").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/requests").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/deliveries").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/drives").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/stats").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/chat").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/donations/donor/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/donations").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/donations/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/v1/requests/recipient/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/requests").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/requests/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/v1/deliveries/coordinator/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/deliveries").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/deliveries/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/drives/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/drives/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/drives/**").hasRole("ADMIN")
                        .anyRequest().permitAll())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    private void writeJson(jakarta.servlet.http.HttpServletResponse response, int status, Map<String, String> body)
            throws IOException {
        response.setStatus(status);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getWriter(), body);
    }
}