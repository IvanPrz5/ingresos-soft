package com.ingresos_soft.Universal.Services;

import com.ingresos_soft.Auth.Services.AuthService;
import com.ingresos_soft.Universal.Models.UsuariosDataSourceModel;
import com.ingresos_soft.Universal.Repositories.DataSourceRepository;
import com.ingresos_soft.Universal.Repositories.UsuariosDataSourceRepository;
import com.ingresos_soft.Universal.Repositories.UsuariosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UsuariosDataSourceService {

    @Autowired
    UsuariosDataSourceRepository usuariosDataSourceRepository;

    @Autowired
    DataSourceRepository dataSourceRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private UsuariosRepository usuariosRepository;

    public UsuariosDataSourceModel get(Long id) {
        return usuariosDataSourceRepository.findById(id).orElseThrow();
    }

    public List<Map<String, Object>> findByIdUsuario(Long idUsuario) {
        try {
            return usuariosDataSourceRepository.findByIdUsuarioAndStatusOrderByIdDataSource(
                            usuariosRepository.findById(idUsuario).orElseThrow(), true)
                    .stream()
                    .map(dS -> {
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", dS.getIdDataSource().getId());
                        map.put("razonSocial", dS.getIdDataSource().getRazonSocial());
                        map.put("rfc", dS.getIdDataSource().getRfc());
                        return map;
                    }).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Plugin: Universal, Service: UsuariosDataSourceService, Method: FindByIdUsuario, Error: ", e);
            throw new NoSuchElementException();
        }
    }

    public UsuariosDataSourceModel asignaEntidad(Long idDataSource, Long idUsuario) {
        try {
            UsuariosDataSourceModel usuariosDataSourceModel;
            Optional<UsuariosDataSourceModel> usuariosDataSource = usuariosDataSourceRepository.findByIdDataSourceAndIdUsuarioAndStatus(
                    dataSourceRepository.findById(idDataSource).orElseThrow(),
                    usuariosRepository.findById(idUsuario).orElseThrow(),
                    true
            );

            if (usuariosDataSource.isPresent()) {
                usuariosDataSourceModel = usuariosDataSource.get();
                usuariosDataSourceModel.setStatus(true);
            } else {
                usuariosDataSourceModel = new UsuariosDataSourceModel();
                usuariosDataSourceModel.setStatus(true);
                usuariosDataSourceModel.setIdDataSource(dataSourceRepository.findById(idDataSource).orElseThrow());
                usuariosDataSourceModel.setIdUsuario(usuariosRepository.findById(idUsuario).orElseThrow());
            }

            return usuariosDataSourceRepository.save(usuariosDataSourceModel);
        } catch (Exception e) {
            log.error("Plugin: Universal, Service: UsuariosDataSourceService, Method: AsignaEntidad, Error: ", e);
            throw new IllegalArgumentException();
        }
    }

    public UsuariosDataSourceModel quitarEntidad(Long idDataSource, Long idUsuario) {
        Optional<UsuariosDataSourceModel> usuarioDataSource = usuariosDataSourceRepository.findByIdDataSourceAndIdUsuarioAndStatus(
                dataSourceRepository.findById(idDataSource).orElseThrow(),
                usuariosRepository.findById(idUsuario).orElseThrow(),
                true
        );
        if (usuarioDataSource.isPresent()) {
            usuarioDataSource.get().setStatus(false);
            return usuariosDataSourceRepository.save(usuarioDataSource.get());
        } else {
            throw new NoSuchElementException();
        }
    }

    public UsuariosDataSourceModel saveOrUpdate(UsuariosDataSourceModel request) {
        try {
            UsuariosDataSourceModel usuariosDataSourceInstance;

            if (request.getId() != null) {
                usuariosDataSourceInstance = this.get(request.getId());
                usuariosDataSourceInstance.setStatus(request.getStatus());
            } else {
                usuariosDataSourceInstance = new UsuariosDataSourceModel();
                usuariosDataSourceInstance.setStatus(true);
            }

            usuariosDataSourceInstance.setIdDataSource(
                    dataSourceRepository.findById(request.getIdDataSource().getId()).orElseThrow());
            usuariosDataSourceInstance.setIdUsuario(authService.getCurrentUser());

            return usuariosDataSourceRepository.save(usuariosDataSourceInstance);
        } catch (Exception e) {
            log.error(
                    "Plugin: Universal, Service: UsuariosDataSourceService, Method: SaveOrUpdate, Error: ", e);
            throw new IllegalArgumentException();
        }
    }

}
