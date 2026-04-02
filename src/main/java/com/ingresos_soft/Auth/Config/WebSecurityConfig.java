package com.ingresos_soft.Auth.Config;

import com.ingresos_soft.Auth.JWT.JWTAuthorizationFilter;
import com.ingresos_soft.Auth.Utils.Token.TokenUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    @PostConstruct
    public void init() {
        TokenUtils.setUserDetailsService(userDetailsService);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
        return http.cors(withDefaults()).csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests.requestMatchers("/api/v1/Auth/**").permitAll()
                        .anyRequest().authenticated()).httpBasic(withDefaults())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
                AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}