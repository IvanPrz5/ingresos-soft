package com.ingresos_soft.Universal.Services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ingresos_soft.Universal.Dto.UsuariosDto;
import com.ingresos_soft.Universal.Models.UsuariosModel;
import com.ingresos_soft.Universal.Repositories.UsuariosRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsuariosService {

    @Autowired
    UsuariosRepository usuariosRepository;

    @Autowired
    UsuariosRolesService usuariosRolesService;

    public UsuariosModel get(Long id) {
        return usuariosRepository.findById(id).get();
    }

    public UsuariosModel findFirst() {
        try {
            return usuariosRepository.findFirstByOrderByIdAsc()
                    .orElseThrow(() -> new NoSuchElementException("No Existe el usuario"));
        } catch (Exception e) {
            log.error("Service: UsuariosService, Method: FindFirst, Error : ", e);
            throw new NoSuchElementException(e);
        }
    }

    public Optional<UsuariosModel> findOneByEmail(String email) {
        try {
            return usuariosRepository.findOneByEmail(email);
        } catch (Exception e) {
            log.error("Error - Service: FindOneByEmail", e);
            throw new NoSuchElementException(e);
        }
    }

    public UsuariosDto findById(Long id) {
        try {
            UsuariosModel usuarioInstance = this.get(id);
            return this.convertToDto(usuarioInstance);
        } catch (Exception e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    public List<UsuariosDto> findAll() {
        try {
            return usuariosRepository.findAll()
                    .stream().map(this::convertToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Plugin: Universal, Service: UsuariosService, Method: FindAllByStatus, Error: ", e);
            throw new IllegalArgumentException();
        }
    }

    public UsuariosDto convertToDto(UsuariosModel u) {
        return new UsuariosDto(
                u.getId(),
                u.getNombre(),
                u.getApPaterno(),
                u.getEmail(),
                null,
                u.getFechaNacimiento(),
                u.getTelefono(),
                u.getStatus(),
                usuariosRolesService.getRolesByUsuario(u.getId()));
    }

    public UsuariosModel saveOrUpdate(UsuariosModel request) {
        try {
            UsuariosModel usuariosInstance;
            if (request.getId() != null) {
                usuariosInstance = this.get(request.getId());
                usuariosInstance.setStatus(request.getStatus());
            } else {
                usuariosInstance = new UsuariosModel();
                usuariosInstance.setStatus(true);
            }

            usuariosInstance.setNombre(request.getNombre());
            usuariosInstance.setApPaterno(request.getApPaterno());
            usuariosInstance.setEmail(request.getEmail());
            if (request.getPassword() != null) {
                usuariosInstance.setPassword(new BCryptPasswordEncoder()
                                                     .encode(request.getPassword().replaceAll("\\s", "")));
            }
            usuariosInstance.setFechaNacimiento(request.getFechaNacimiento());
            usuariosInstance.setTelefono(request.getTelefono());

            return usuariosRepository.save(usuariosInstance);
        } catch (Exception e) {
            log.error("Service: UsuariosService, Method: Save, Error : ", e);
            throw new IllegalArgumentException();
        }
    }

}