package com.ingresos_soft.Universal.Services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingresos_soft.Universal.Models.MenusModel;
import com.ingresos_soft.Universal.Models.RolesMenusModel;
import com.ingresos_soft.Universal.Repositories.MenusRepository;
import com.ingresos_soft.Universal.Repositories.RolesMenusRepository;
import com.ingresos_soft.Universal.Repositories.RolesRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RolesMenusService {

    @Autowired
    RolesMenusRepository rolesMenusRepository;

    @Autowired
    MenusRepository menusRepository;

    @Autowired
    RolesRepository rolesRepository;

    public RolesMenusModel asignaMenuInRol(Long idMenu, Long idRol) {
        try {
            RolesMenusModel rolesMenusInstance;
            Optional<RolesMenusModel> roleMenu = rolesMenusRepository.findByIdMenuAndIdRolAndStatus(
                    menusRepository.findById(idMenu).get(), rolesRepository.findById(idRol).get(), false);
            if (roleMenu.isPresent()) {
                rolesMenusInstance = roleMenu.get();
                rolesMenusInstance.setStatus(true);
            } else {
                Optional<MenusModel> menuInstance = menusRepository.findById(idMenu);
                if (menuInstance.isPresent() && menuInstance.get().getIdMenuDepende() != null) {

                    Optional<RolesMenusModel> relacionExistente = rolesMenusRepository
                            .findByIdMenuAndIdRolAndStatus(menuInstance.get().getIdMenuDepende(),
                                                           rolesRepository.findById(idRol).get(), true);

                    if (!relacionExistente.isPresent()) {
                        rolesMenusRepository.save(new RolesMenusModel(
                                menusRepository.findById(menuInstance.get().getIdMenuDepende().getId()).get(),
                                rolesRepository.findById(idRol).get(), true));
                    }
                }
                rolesMenusInstance = new RolesMenusModel();
                rolesMenusInstance.setStatus(true);
                rolesMenusInstance.setIdMenu(menusRepository.findById(idMenu).get());
                rolesMenusInstance.setIdRol(rolesRepository.findById(idRol).get());
            }
            return rolesMenusRepository.save(rolesMenusInstance);
        } catch (Exception e) {
            log.error("Plugin: Universal, Service: RolesMenusService, Method: AsignaMenuInRol, Error: ", e);
            throw new IllegalArgumentException();
        }
    }

    public RolesMenusModel quitarMenuToRol(Long idMenu, Long idRol) {
        try {
            Optional<RolesMenusModel> rolesMenusInstance = rolesMenusRepository
                    .findByIdMenuAndIdRolAndStatus(menusRepository.findById(idMenu).get(),
                                                   rolesRepository.findById(idRol).get(), true);

            if (rolesMenusInstance.isPresent()) {
                rolesMenusInstance.get().setStatus(false);
                return rolesMenusRepository.save(rolesMenusInstance.get());
            } else {
                throw new NoSuchElementException();
            }

        } catch (Exception e) {
            log.error("Plugin: Universal, Service: RolesMenusService, Method: QuitarMenuToRol, Error: ", e);
            throw new IllegalArgumentException();
        }
    }
}