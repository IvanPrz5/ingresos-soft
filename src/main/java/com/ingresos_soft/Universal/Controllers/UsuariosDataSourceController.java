package com.ingresos_soft.Universal.Controllers;

import com.ingresos_soft.Auth.Services.AuthService;
import com.ingresos_soft.Universal.Models.UsuariosDataSourceModel;
import com.ingresos_soft.Universal.Models.UsuariosModel;
import com.ingresos_soft.Universal.Repositories.UsuariosDataSourceRepository;
import com.ingresos_soft.Universal.Services.UsuariosDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ingresos_soft.Universal.Services.RolesMenusService;
import com.ingresos_soft.Universal.Utils.MessageResponse;
import com.ingresos_soft.Universal.Utils.ResultObjectResponse;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1/UsuariosDataSource")
@Slf4j
public class UsuariosDataSourceController {

    @Autowired
    UsuariosDataSourceService usuariosDataSourceService;

    @Autowired
    AuthService authService;

    @Autowired
    UsuariosDataSourceRepository usuariosDataSourceRepository;

    @GetMapping("/findDataSourceByUsuario/{idUsuario}")
    public ResponseEntity<ResultObjectResponse> findDataSourceByUsuario(@PathVariable("idUsuario") Long idUsuario) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(MessageResponse.success(usuariosDataSourceService.findByIdUsuario(idUsuario)));
        } catch (Exception e) {
            log.error(
                    "Plugin: Universal, Controller: UsuarioEntidadesController, Method: FindEntidadesByUsuario, Error: ",
                    e);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(MessageResponse.error(null, null));
        }
    }

    @PostMapping("/asignaDataSource/{idDataSource}/{idUsuario}")
    public ResponseEntity<ResultObjectResponse> asignaEntidad(@PathVariable("idDataSource") Long idDataSource,
                                                              @PathVariable("idUsuario") Long idUsuario) {
        try {
            usuariosDataSourceService.asignaEntidad(idDataSource, idUsuario);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(MessageResponse.success(null));
        } catch (Exception e) {
            log.error("Plugin: Universal, Controller: UsuarioEntidadesController, Method: AsignaEntidad, Error: ", e);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(MessageResponse.error(null, null));
        }
    }

    @PostMapping("/quitarDataSource/{idDataSource}/{idUsuario}")
    public ResponseEntity<ResultObjectResponse> quitarEntidad(@PathVariable("idDataSource") Long idDataSource,
                                                              @PathVariable("idUsuario") Long idUsuario) {
        try {
            usuariosDataSourceService.quitarEntidad(idDataSource, idUsuario);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(MessageResponse.success(null));
        } catch (Exception e) {
            log.error("Plugin: Universal, Controller: UsuarioEntidadesController, Method: QuitarEntidad, Error: ", e);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(MessageResponse.error(null, null));
        }
    }

    @PostMapping("/seleccionarDataSource/{idDataSource}")
    public ResponseEntity<ResultObjectResponse> seleccionarDataSource(@PathVariable("idDataSource") Long idDataSource) {
        try {
            UsuariosModel usuario = authService.getCurrentUser();

            // Obtener todas las entidades del usuario
            List<UsuariosDataSourceModel> todosDataSource = usuariosDataSourceRepository
                    .findByIdUsuarioAndStatusOrderByIdDataSource(usuario, true);

            // Verificar que el usuario tiene acceso a esta entidad
            boolean tieneAcceso = todosDataSource.stream()
                    .anyMatch(ue -> ue.getIdDataSource().getId().equals(idDataSource));

            if (!tieneAcceso) {
                return ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .body(MessageResponse.error("No tiene acceso a este Data Source", null));
            }

            // Desmarcar todas las entidades del usuario
            todosDataSource.forEach(ue -> ue.setSeleccionada(false));
            usuariosDataSourceRepository.saveAll(todosDataSource);

            // Marcar la entidad seleccionada
            UsuariosDataSourceModel dataSourceSeleccionada = todosDataSource.stream()
                    .filter(ue -> ue.getIdDataSource().getId().equals(idDataSource))
                    .findFirst()
                    .orElseThrow();

            dataSourceSeleccionada.setSeleccionada(true);
            usuariosDataSourceRepository.save(dataSourceSeleccionada);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(MessageResponse.success(dataSourceSeleccionada.getIdDataSource()));
        } catch (Exception e) {
            log.error("Error al seleccionar entidad: ", e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(MessageResponse.error("Error al seleccionar entidad", null));
        }
    }


}
