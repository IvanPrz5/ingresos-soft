package com.ingresos_soft.Universal.Controllers;

import com.ingresos_soft.Universal.Services.UsuariosRolesService;
import com.ingresos_soft.Universal.Utils.MessageResponse;
import com.ingresos_soft.Universal.Utils.ResultObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1/UsuariosRoles")
@Slf4j
public class UsuariosRolesController {

    @Autowired
    UsuariosRolesService usuariosRolesService;

    @GetMapping("/findRolesByUsuario/{idUsuario}")
    public ResponseEntity<ResultObjectResponse> findRolesByUsuario(@PathVariable("idUsuario") Long idUsuario) {
        try {
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(MessageResponse.success(usuariosRolesService.getRolesByUsuario(idUsuario)));
        } catch (Exception e) {
            log.error("Plugin: Universal, Controller: UsuariosRolesController, Method: FindRolesByUsuario, Error: ", e);
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(MessageResponse.error(null, null));
        }
    }

    @PostMapping("/asignaRol/{idUsuario}/{idRol}")
    public ResponseEntity<ResultObjectResponse> asignaRol(@PathVariable("idUsuario") Long idUsuario,
                                                          @PathVariable("idRol") Long idRol) {
        try {
            usuariosRolesService.asignaRol(idUsuario, idRol);
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(MessageResponse.success(null));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(MessageResponse.error(null, null));
        }
    }

    @PostMapping("/quitarRol/{idUsuario}/{idRol}")
    public ResponseEntity<ResultObjectResponse> quitarRol(@PathVariable("idUsuario") Long idUsuario,
                                                          @PathVariable("idRol") Long idRol) {
        try {
            usuariosRolesService.quitarRol(idUsuario, idRol);
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(MessageResponse.success(null));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(MessageResponse.error(null, null));
        }
    }
}