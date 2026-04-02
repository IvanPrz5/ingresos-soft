package com.ingresos_soft.Universal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ingresos_soft.Universal.Models.MenusModel;
import com.ingresos_soft.Universal.Services.MenusService;
import com.ingresos_soft.Universal.Utils.MessageResponse;
import com.ingresos_soft.Universal.Utils.ResultObjectResponse;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1/Menus")
@Slf4j
public class MenusController {

    @Autowired
    MenusService menusService;

    @GetMapping("/findById/{id}")
    public ResponseEntity<ResultObjectResponse> findById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(MessageResponse.success(menusService.findById(id)));
        } catch (Exception e) {
            log.error("Plugin: Universal, Controller: MenusController, Method: FindById, Error: ", e);
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(MessageResponse.error(null, null));
        }
    }

    @GetMapping("/findAllMenus")
    public ResponseEntity<ResultObjectResponse> findAllMenus() {
        try {
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(MessageResponse.success(menusService.findAllMenus()));
        } catch (Exception e) {
            log.error("Plugin: Universal, Controller: MenusController, Method: findAllMenus, Error: ", e);
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(MessageResponse.error(null, null));
        }
    }

    @GetMapping("/findByIdModule/{idModule}")
    public ResponseEntity<ResultObjectResponse> findByIdModule(@PathVariable("idModule") Long idModule) {
        try {
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(MessageResponse.success(menusService.findByIdModule(idModule)));
        } catch (Exception e) {
            log.error("Plugin: Universal, Controller: MenusController, Method: FindByIdModule, Error: ", e);
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(MessageResponse.error(null, null));
        }
    }

    @GetMapping("/findMenusByRole/{idRol}")
    public ResponseEntity<ResultObjectResponse> findMenusByRole(@PathVariable("idRol") Long idRol) {
        try {
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(MessageResponse.success(menusService.findMenusByRol(idRol)));
        } catch (Exception e) {
            log.error("Plugin: Universal, Controller: MenusController, Method: FindMenusByIdUsuario, Error: ", e);
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(MessageResponse.error(null, null));
        }
    }

    @GetMapping("/findMenusByRolAndModule/{idUsuario}/{idModule}")
    public ResponseEntity<ResultObjectResponse> findMenusByRolAndModule(@PathVariable("idUsuario") Long idUsuario,
                                                                        @PathVariable("idModule") Long idModule) {
        try {
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(MessageResponse.success(
                            menusService.findMenusByRolAndModule(idUsuario, idModule)));
        } catch (Exception e) {
            log.error("Plugin: Universal, Controller: MenusController, Method: FindMenusByRolAndModule, Error: ", e);
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(MessageResponse.error(null, null));
        }
    }

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<ResultObjectResponse> saveOrUpdate(@RequestBody MenusModel request) {
        try {
            MenusModel menuInstance = menusService.saveOrUpdate(request);
            if (menuInstance != null) {
                return ResponseEntity
                        .status(HttpStatus.ACCEPTED)
                        .body(MessageResponse.success(null));
            } else {
                return ResponseEntity
                        .status(HttpStatus.ACCEPTED)
                        .body(MessageResponse.error(null, "La descripcion o la ruta ya existe."));
            }
        } catch (Exception e) {
            log.error("Plugin: Universal, Controller: MenusController, Method: SaveOrUpdate, Error: ", e);
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(MessageResponse.error(null, null));
        }
    }
}