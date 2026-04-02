package com.ingresos_soft.Universal.Init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Component
@Order(1)
@Slf4j
public class RunnerUniversal implements CommandLineRunner {

    @Autowired
    InitUniversalService initUniversalService;

    @Override
    @Transactional(transactionManager = "universalTransactionManager")
    public void run(String... args) {
        try {
            initUniversalService.initModules();
            initUniversalService.initDataSource();
            initUniversalService.initUsuarios();
            initUniversalService.initRoles();
            initUniversalService.initUsuariosRoles();
            initUniversalService.initMenus();
            initUniversalService.initRolesMenus();
            initUniversalService.initUsuariosDataSource();
        } catch (Exception e) {
            log.error("Plugin: Universal, Runner: RunnerUniversal, Method: Run, Error: ", e);
            throw new RuntimeException();
        }
    }
}