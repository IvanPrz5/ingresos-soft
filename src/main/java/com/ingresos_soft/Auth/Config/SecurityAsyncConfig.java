package com.ingresos_soft.Auth.Config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class SecurityAsyncConfig {

    @PostConstruct
    public void init() {
        // Esto es lo que asegura que el contexto de seguridad se herede en hilos @Async
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }
}
