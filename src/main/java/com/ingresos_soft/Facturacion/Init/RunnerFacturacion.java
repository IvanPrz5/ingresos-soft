package com.ingresos_soft.Facturacion.Init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Order(3)
@Slf4j
public class RunnerFacturacion implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // Inicialización deshabilitada - Usar endpoint: POST /api/admin/init-entity/{idEntidad}
        log.info("RunnerFacturacion - Inicialización manual habilitada. Usar: POST /api/admin/init-entity/{idEntidad}");
    }

}