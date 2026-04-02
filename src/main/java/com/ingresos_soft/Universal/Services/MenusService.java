package com.ingresos_soft.Universal.Services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingresos_soft.Universal.Dto.MenusDto;
import com.ingresos_soft.Universal.Models.MenusModel;
import com.ingresos_soft.Universal.Models.UsuariosRolesModel;
import com.ingresos_soft.Universal.Repositories.MenusRepository;
import com.ingresos_soft.Universal.Repositories.ModulesRepository;
import com.ingresos_soft.Universal.Repositories.RolesMenusRepository;
import com.ingresos_soft.Universal.Repositories.UsuariosRepository;
import com.ingresos_soft.Universal.Repositories.UsuariosRolesRepository;

import jakarta.persistence.Tuple;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MenusService {
    @Autowired
    MenusRepository menusRepository;

    @Autowired
    ModulesRepository modulesRepository;

    @Autowired
    RolesMenusRepository rolesMenusRepository;

    @Autowired
    UsuariosRolesRepository usuariosRolesRepository;

    @Autowired
    UsuariosRepository usuariosRepository;

    public MenusModel get(Long id) {
        return menusRepository.findById(id).get();
    }

    public MenusModel findById(Long id) {
        try {
            return this.get(id);
        } catch (Exception e) {
            log.error("Plugin: Universal, Service: MenusService, Method: FindById, Error: ", e);
            throw new NoSuchElementException();
        }
    }

    public List<MenusDto> findAllMenus() {
        try {
            List<Tuple> list = menusRepository.findAllMenusByStatus();
            if (!list.isEmpty()) {
                return list.stream()
                        .map(m -> buildMenuMap(0, m, null, null))
                        .collect(Collectors.toList());
            } else {
                return Collections.emptyList();
            }
        } catch (Exception e) {
            log.error("Plugin: Universal, Service: MenusService, Method: FindAll, Error: ", e);
            throw new NoSuchElementException();
        }
    }

    public List<MenusModel> findByIdModule(Long idModule) {
        try {
            return menusRepository.findByIdModule(modulesRepository.findById(idModule).orElseThrow());
        } catch (Exception e) {
            log.error("Plugin: Universal, Service: MenusService, Method: FindByIdModule, Error: ", e);
            throw new NoSuchElementException();
        }
    }

    public List<MenusDto> findMenusByRolAndModule(Long idUsuario, Long idModule) {
        try {
            List<Long> rolesList = new ArrayList<>();
            List<UsuariosRolesModel> usuariosRolesList = usuariosRolesRepository
                    .findByIdUsuario(usuariosRepository.findById(idUsuario).orElseThrow());

            for (UsuariosRolesModel usuarioRol : usuariosRolesList) {
                rolesList.add(usuarioRol.getIdRol().getId());
            }

            List<Tuple> list = menusRepository.findMenusByRolAndModule(rolesList, idModule);
            if (!list.isEmpty()) {
                return list.stream()
                        .map(t -> buildMenuMap(1, t, rolesList, null))
                        .collect(Collectors.toList());
            } else {
                return Collections.emptyList();
            }
        } catch (Exception e) {
            log.error("Plugin: Universal, Service: UsuariosMenusService, Method: findMenusByRolAndModule, Error: ", e);
            throw new NoSuchElementException();
        }
    }

    public List<MenusDto> findMenusByRol(Long idRole) {
        try {
            List<Tuple> list = menusRepository.findMenusByRole(idRole);
            if (!list.isEmpty()) {
                return list.stream()
                        .map(t -> buildMenuMap(2, t, null, idRole))
                        .collect(Collectors.toList());
            } else {
                return Collections.emptyList();
            }
        } catch (Exception e) {
            log.error("Plugin: Universal, Service: UsuariosMenusService, Method: FindMenusByUsuario, Error: ", e);
            throw new NoSuchElementException();
        }
    }

    // Se crea el metodo para construir recursivamente el map
    private MenusDto buildMenuMap(Integer tipo, Tuple menu, List<Long> rolesList, Long idRole) {
        return new MenusDto(
                (Long) menu.get("id"),
                (String) menu.get("descripcion"),
                (String) menu.get("icon"),
                (String) menu.get("ruta"),
                (Boolean) menu.get("status"),
                findSubMenusRecursively(tipo, rolesList != null
                                              ? rolesList
                                              : null, (Long) menu.get("id"), idRole));
    }

    // Meotodo recursivo para la cantidad de n submenus
    private List<MenusDto> findSubMenusRecursively(Integer tipo, List<Long> rolesList, Long idMenuDepende,
                                                   Long idRole) {
        List<Tuple> subMenusList = new ArrayList<>();

        if (tipo == 0) {
            subMenusList = menusRepository.findSubMenusByIdMenuDepende(idMenuDepende);
        } else if (tipo == 1) {
            subMenusList = menusRepository.findSubMenusByIdMenuDependeAndRolesList(rolesList, idMenuDepende);
        } else if (tipo == 2) {
            subMenusList = menusRepository.findSubMenusByRoleAndIdMenuDepende(idRole, idMenuDepende);
        }

        if (subMenusList.isEmpty()) {
            return Collections.emptyList();
        }

        return subMenusList.stream()
                .map(subMenu -> new MenusDto(
                        (Long) subMenu.get("id"),
                        (String) subMenu.get("descripcion"),
                        (String) subMenu.get("icon"),
                        (String) subMenu.get("ruta"),
                        (Boolean) subMenu.get("status"),
                        findSubMenusRecursively(tipo, rolesList, (Long) subMenu.get("id"), idRole)))
                .collect(Collectors.toList());
    }

    public MenusModel saveOrUpdate(MenusModel request) {
        try {
            if (!menusRepository.findByDescripcion(request.getDescripcion()).isPresent()
                    || !menusRepository.findByRuta(request.getRuta()).isPresent()) {

                MenusModel menusInstance;
                if (request.getId() != null) {
                    menusInstance = this.get(request.getId());
                    menusInstance.setStatus(request.getStatus());
                } else {
                    menusInstance = new MenusModel();
                    menusInstance.setStatus(true);
                }
                menusInstance.setDescripcion(request.getDescripcion());
                menusInstance.setIcon(request.getIcon());

                if (request.getIdMenuDepende() != null) {
                    menusInstance.setIdMenuDepende(
                            menusRepository.findById(request.getIdMenuDepende().getId()).orElseThrow());
                }

                if (request.getIdModule() != null) {
                    menusInstance.setIdModule(modulesRepository.findById(request.getIdModule().getId()).orElseThrow());
                }

                menusInstance.setRuta(request.getRuta());

                return menusRepository.save(menusInstance);
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("Plugin: Universal, Service: MenusService, Method: SaveOrUpdate, Error: ", e);
            throw new IllegalArgumentException();
        }
    }
}