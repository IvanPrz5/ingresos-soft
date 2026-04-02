package com.ingresos_soft.Ingresos.Controllers.Core;

import com.ingresos_soft.Ingresos.Models.Core.EntidadesModel;
import com.ingresos_soft.Ingresos.Services.Core.EntidadesService;
import com.ingresos_soft.Universal.Utils.MessageResponse;
import com.ingresos_soft.Universal.Utils.ResultObjectResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1/Entidades")
@Slf4j
public class EntidadesController {

    @Autowired
    EntidadesService entidadesService;

    @GetMapping("/findById/{id}")
    public ResponseEntity<ResultObjectResponse> findById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(MessageResponse.success(entidadesService.findById(id)));
        } catch (Exception e) {
            log.error("Plugin: Universal, Controller: EmpresasController, Method: FindById, Error : ", e);
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(MessageResponse.error(null, null));
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<ResultObjectResponse> findAll() {
        try {
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(MessageResponse.success(entidadesService.findAll()));
        } catch (Exception e) {
            log.error("Plugin: Universal, Controller: EmpresasController, Method: findAll, Error : ",
                      e);
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(MessageResponse.error(null, null));
        }
    }

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<ResultObjectResponse> saveOrUpdate(
            @RequestPart(value = "doc") EntidadesModel request,
            @RequestPart(value = "cer", required = false) MultipartFile cer,
            @RequestPart(value = "key", required = false) MultipartFile key,
            @RequestPart(value = "formato", required = false) MultipartFile formato
    ) {
        try {
            if (request.getId() == null) {
                EntidadesModel entidad = entidadesService.findByRazonSocial(request.getRazonSocial());
                if (entidad != null) {
                    return ResponseEntity
                            .status(HttpStatus.NOT_ACCEPTABLE)
                            .body(MessageResponse.error(null,
                                                        "La entidad ya existe, por favor ingrese una diferente"));
                }
                entidadesService.saveOrUpdate(request, cer, key, formato);
                return ResponseEntity
                        .status(HttpStatus.ACCEPTED)
                        .body(MessageResponse.success(null));

            } else {
                entidadesService.saveOrUpdate(request, cer, key, formato);
                return ResponseEntity
                        .status(HttpStatus.ACCEPTED)
                        .body(MessageResponse.success(null));
            }
        } catch (Exception e) {
            log.error("Plugin: Universal, Controller: EntidadesController, Method: SaveOrUpdate, Error : ", e);
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(MessageResponse.error(null, null));
        }
    }
}
