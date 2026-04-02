package com.ingresos_soft.Universal.Services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingresos_soft.Universal.Dto.RolesDto;
import com.ingresos_soft.Universal.Models.RolesModel;
import com.ingresos_soft.Universal.Models.UsuariosModel;
import com.ingresos_soft.Universal.Models.UsuariosRolesModel;
import com.ingresos_soft.Universal.Repositories.RolesRepository;
import com.ingresos_soft.Universal.Repositories.UsuariosRepository;
import com.ingresos_soft.Universal.Repositories.UsuariosRolesRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsuariosRolesService {

    @Autowired
    UsuariosRolesRepository usuarioRolRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    UsuariosRepository usuariosRepository;

    public Boolean isAuthorizated(Long idUsuario) {
        try {
            RolesModel roleInstance = rolesRepository.findFirstByOrderByIdAsc().get();
            UsuariosModel usuarioInstance = usuariosRepository.findById(idUsuario).get();
            Optional<UsuariosRolesModel> usuariosRolesInstance = usuarioRolRepository
                    .findByIdUsuarioAndIdRolAndStatus(usuarioInstance, roleInstance, true);
            return usuariosRolesInstance.isPresent();
        } catch (Exception e) {
            log.error("Plugin: Universal, Service: UsuarioRolesService, Method: isAuthorizated, Error: ", e);
            throw new NoSuchElementException();
        }
    }

    public List<RolesDto> getRolesByUsuario(Long idUsuario) {
        try {
            return usuarioRolRepository.getRolesByUsuario(idUsuario).stream()
                    .map(tuple -> new RolesDto(
                            (Long) tuple.get("id"),
                            (String) tuple.get("descripcion"),
                            (Boolean) tuple.get("status")))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new NoSuchElementException();
        }
    }

    public UsuariosRolesModel save(UsuariosRolesModel request) {
        try {
            return usuarioRolRepository.save(request);
        } catch (Exception e) {
            log.error("Service: UsuariosService, Method: Save, Error", e);
            throw new IllegalArgumentException(e);
        }
    }

    public UsuariosRolesModel asignaRol(Long idUsuario, Long idRol) {
        try {
            UsuariosRolesModel usuariosRolesInstance;
            Optional<UsuariosRolesModel> usuarioRol = usuarioRolRepository
                    .findByIdUsuarioAndIdRolAndStatus(usuariosRepository.findById(idUsuario).get(),
                                                      rolesRepository.findById(idRol).get(), false);

            if (usuarioRol.isPresent()) {
                usuariosRolesInstance = usuarioRol.get();
                usuariosRolesInstance.setStatus(true);
            } else {
                usuariosRolesInstance = new UsuariosRolesModel();
                usuariosRolesInstance.setStatus(true);
                usuariosRolesInstance.setIdUsuario(usuariosRepository.findById(idUsuario).get());
                usuariosRolesInstance.setIdRol(rolesRepository.findById(idRol).get());
            }

            return usuarioRolRepository.save(usuariosRolesInstance);
        } catch (Exception e) {
            log.error("Plugin: Universal, Service: UsuariosRolesService, Method: AsignaRol, Error: ", e);
            throw new IllegalArgumentException();
        }
    }

    public UsuariosRolesModel quitarRol(Long idUsuario, Long idRol) {
        Optional<UsuariosRolesModel> usuarioRolInstance = usuarioRolRepository
                .findByIdUsuarioAndIdRolAndStatus(usuariosRepository.findById(idUsuario).get(),
                                                  rolesRepository.findById(idRol).get(), true);
        if (usuarioRolInstance.isPresent()) {
            usuarioRolInstance.get().setStatus(false);
            return usuarioRolRepository.save(usuarioRolInstance.get());
        } else {
            throw new NoSuchElementException();
        }
    }
}