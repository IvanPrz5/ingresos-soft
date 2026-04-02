package com.ingresos_soft.Universal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ingresos_soft.Universal.Services.RolesMenusService;
import com.ingresos_soft.Universal.Utils.MessageResponse;
import com.ingresos_soft.Universal.Utils.ResultObjectResponse;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1/RolesMenus")
@Slf4j
public class RolesMenusController {

    @Autowired
    RolesMenusService rolesMenusService;

    @PostMapping("/asignaMenuInRol/{idMenu}/{idRol}")
    public ResponseEntity<ResultObjectResponse> asignaMenuInRol(@PathVariable("idMenu") Long idMenu,
                                                                @PathVariable Long idRol) {
        try {
            rolesMenusService.asignaMenuInRol(idMenu, idRol);
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(MessageResponse.success(null));
        } catch (Exception e) {
            log.error("Plugin: Universal, Controller: RolesMenusController, Method: AsignaMenuInRol, Error: ", e);
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(MessageResponse.error(null, null));
        }
    }

    @PostMapping("/quitarMenuToRol/{idMenu}/{idRol}")
    public ResponseEntity<ResultObjectResponse> quitarMenuToRol(@PathVariable("idMenu") Long idMenu,
                                                                @PathVariable("idRol") Long idRol) {
        try {
            rolesMenusService.quitarMenuToRol(idMenu, idRol);
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(MessageResponse.success(null));
        } catch (Exception e) {
            log.error("Plugin: Universal, Controller: RolesMenusController, Method: QuitarMenuToRol, Error: ", e);
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(MessageResponse.error(null, null));
        }
    }
}