package com.ingresos_soft.Universal.Init;

import java.time.LocalDate;
import java.util.List;

import com.ingresos_soft.Universal.Models.*;
import com.ingresos_soft.Universal.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InitUniversalService {

    @Autowired
    ModulesRepository modulesRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    UsuariosRepository usuariosRepository;

    @Autowired
    UsuariosRolesRepository usuariosRolesRepository;

    @Autowired
    MenusRepository menusRepository;

    @Autowired
    RolesMenusRepository rolesMenusRepository;

    @Autowired
    UsuariosDataSourceRepository usuariosDataSourceRepository;

    @Autowired
    DataSourceRepository dataSourceRepository;

    public void initModules() {
        try {
            if (modulesRepository.count() == 0) {
                modulesRepository.save(new ModulesModel("Facturación",
                                                        "Sistema de Facturación",
                                                        "https://cdn.vuetifyjs.com/images/parallax/material.jpg",
                                                        "/facturacion/home-facturacion",
                                                        true));
                modulesRepository.save(new ModulesModel("Configuración",
                                                        "Configuración de Sistema",
                                                        "https://cdn.vuetifyjs.com/images/parallax/material2.jpg",
                                                        "/configuracion/home-configuracion",
                                                        true));
            }
        } catch (Exception e) {
            log.error("Plugin: Universal, Init: InitUniversal, Method: InitModules, Error: ",
                      e);
            throw new RuntimeException();
        }
    }

    public void initDataSource() {
        try {
            if (dataSourceRepository.count() == 0) {
                dataSourceRepository.save(
                        new DataSourceModel("ORGANICOS ÑAVEZ OSORIO",
                                            "OÑO120726RX3",
                                            "organicosIDB",
                                            true));
                dataSourceRepository.save(
                        new DataSourceModel("KERNEL INDUSTIA JUGUETERA",
                                            "KIJ0906199R1",
                                            "kernelIDB",
                                            true));
            }
        } catch (Exception e) {
            log.error("Plugin: Universal, Init: InitUniversal, Method: InitEntidades, Error: ",
                      e);
            throw new RuntimeException();
        }
    }

    public void initUsuarios() {
        try {
            if (usuariosRepository.count() == 0) {
                usuariosRepository.save(
                        new UsuariosModel("Cristian",
                                          "Martinez",
                                          "email@gmail.com",
                                          new BCryptPasswordEncoder().encode("adminG5"),
                                          LocalDate.of(1997,
                                                       4,
                                                       10),
                                          9511234567L,
                                          true));
            }
        } catch (Exception e) {
            log.error("Plugin: Universal, Init: InitUniversal, Method: InitUsuarios, Error: ",
                      e);
            throw new RuntimeException();
        }
    }

    public void initRoles() {
        try {
            if (rolesRepository.count() == 0) {
                rolesRepository.save(new RolesModel("ROLE_DEV",
                                                    true));
                rolesRepository.save(new RolesModel("ROLE_ADMIN",
                                                    true));
                rolesRepository.save(new RolesModel("ROLE_CURRENT",
                                                    true));
            }
        } catch (Exception e) {
            log.error("Plugin: Universal, Init: InitUniversal, Method: InitRoles, Error: ",
                      e);
            throw new RuntimeException(e);
        }
    }

    public void initUsuariosRoles() {
        try {
            if (usuariosRolesRepository.count() == 0) {
                usuariosRolesRepository.save(new UsuariosRolesModel(usuariosRepository.findFirstByOrderByIdAsc()
                                                                                      .get(),
                                                                    rolesRepository.findFirstByOrderByIdAsc()
                                                                                   .get(),
                                                                    true));
            }
        } catch (Exception e) {
            log.error("Plugin: Universal, Init: InitUniversal, Method: InitUsuariosRoles, Error: ",
                      e);
            throw new RuntimeException();
        }
    }

    public void initMenus() {
        try {
            if (menusRepository.count() == 0) {
                /* Module Nomina */
                ModulesModel moduleNomina = modulesRepository.findByNombre("Facturación")
                                                             .get();
                menusRepository.save(
                        new MenusModel("Home",
                                       "/facturacion/home-facturacion",
                                       "mdi-home",
                                       null,
                                       moduleNomina,
                                       true));
                menusRepository.save(
                        new MenusModel("Data Sources",
                                       "/facturacion/data-sources",
                                       "mdi-bank",
                                       null,
                                       moduleNomina,
                                       true));

                /* Module Configuracion */
                ModulesModel moduleConfig = modulesRepository.findByNombre("Configuración")
                                                             .get();
                menusRepository.save(
                        new MenusModel("Configuracion",
                                       "/configuracion/home-configuracion",
                                       "mdi-home",
                                       null,
                                       moduleConfig,
                                       true));
                menusRepository.save(
                        new MenusModel("Usuarios",
                                       "/configuracion/usuarios",
                                       "mdi-account-group",
                                       null,
                                       moduleConfig,
                                       true));
                menusRepository.save(
                        new MenusModel("Roles",
                                       "/configuracion/roles",
                                       "mdi-account-tie",
                                       null,
                                       moduleConfig,
                                       true));
                menusRepository.save(
                        new MenusModel("Menus",
                                       "/configuracion/menus",
                                       "mdi-menu",
                                       null,
                                       moduleConfig,
                                       true));
                menusRepository.save(
                        new MenusModel("Data Sources",
                                       "/configuracion/data-sources",
                                       "mdi-bank",
                                       null,
                                       moduleConfig,
                                       true));
                menusRepository.save(
                        new MenusModel("Entidades",
                                       "/configuracion/entidades",
                                       "mdi-fireplace-off",
                                       null,
                                       moduleConfig,
                                       true));
            }
        } catch (Exception e) {
            log.error("Plugin: Universal, Init: InitUniversal, Method: InitMenus, Error: ",
                      e);
            throw new RuntimeException();
        }
    }

    public void initRolesMenus() {
        try {
            if (rolesMenusRepository.count() == 0) {
                List<MenusModel> menusList = menusRepository.findAllByStatus(true);
                RolesModel rolesInstance = rolesRepository.findFirstByOrderByIdAsc()
                                                          .get();
                for (int i = 0; i < menusList.size(); i++) {
                    rolesMenusRepository.save(new RolesMenusModel(menusList.get(i),
                                                                  rolesInstance,
                                                                  true));
                }
            }
        } catch (Exception e) {
            log.error("Plugin: Universal, Init: InitUniversal, Method InitMenusRoles, Error: ",
                      e);
            throw new RuntimeException();
        }
    }

    public void initUsuariosDataSource() {
        try {
            if (usuariosDataSourceRepository.count() == 0) {
                List<DataSourceModel> dataSources = dataSourceRepository.findByStatus(true);
                UsuariosModel usuariosModel = usuariosRepository.findFirstByOrderByIdAsc()
                                                                .orElseThrow();

                for (int i = 0; i < dataSources.size(); i++) {
                    usuariosDataSourceRepository.save(
                            new UsuariosDataSourceModel(dataSources.get(i),
                                                        usuariosModel,
                                                        true));
                }
            }
        } catch (Exception e) {
            log.error("Plugin: Nomina, Init: InitNomina, Method: InitUsuarioEntidades, Error: ",
                      e);
            throw new RuntimeException();
        }
    }
}