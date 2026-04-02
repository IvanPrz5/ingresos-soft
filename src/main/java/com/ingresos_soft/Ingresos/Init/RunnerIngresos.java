package com.ingresos_soft.Ingresos.Init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@Slf4j
public class RunnerIngresos implements CommandLineRunner {

    @Override
    public void run(String... args) {
        log.info("RunnerIngresos - Inicialización manual habilitada. Usar endpoint en DataSourceController");
    }
}
