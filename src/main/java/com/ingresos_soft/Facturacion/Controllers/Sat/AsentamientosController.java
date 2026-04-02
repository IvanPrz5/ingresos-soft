package com.ingresos_soft.Facturacion.Controllers.Sat;

import com.ingresos_soft.Facturacion.Services.Sat.AsentamientosService;
import com.ingresos_soft.Universal.Utils.MessageResponse;
import com.ingresos_soft.Universal.Utils.ResultObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RestController
@RequestMapping("/api/v1/Asentamientos")
@Slf4j
public class AsentamientosController {

    @Autowired
    AsentamientosService asentamientosService;

    @GetMapping("/findByCodigoPostal/{codigoPostal}")
    public ResponseEntity<ResultObjectResponse> findByCodigoPostal(@PathVariable("codigoPostal") String codigo) {
        try {
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(MessageResponse.success(asentamientosService.findByCodigoPostal(codigo)));
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Controller: AsentamientosController, Method: FindByCodigoPostal, Error: ",
                    e);
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(MessageResponse.error(null, null));
        }
    }
}
