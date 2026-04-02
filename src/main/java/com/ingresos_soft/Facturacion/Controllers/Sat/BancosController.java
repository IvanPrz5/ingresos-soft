package com.ingresos_soft.Facturacion.Controllers.Sat;

import com.ingresos_soft.Facturacion.Models.Sat.BancosModel;
import com.ingresos_soft.Facturacion.Services.Sat.BancosService;
import com.ingresos_soft.Universal.Utils.MessageResponse;
import com.ingresos_soft.Universal.Utils.ResultObjectResponse;
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


import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1/Bancos")
@Slf4j
public class BancosController {

    @Autowired
    BancosService bancosService;

    @GetMapping("/findById/{id}")
    public ResponseEntity<ResultObjectResponse> findById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(MessageResponse.success(bancosService.findById(id)));
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Controller: BancosController, Method: FindById", e);
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
                    .body(MessageResponse.success(bancosService.findAll()));
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Controller: BancosController, Methods: FindAll, Error : ", e);
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(MessageResponse.error(null, null));
        }
    }

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<ResultObjectResponse> saveOrUpdate(@RequestBody BancosModel request) {
        try {
            bancosService.saveOrUpdate(request);
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(MessageResponse.success(null));
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Controller: BancosController, Method: Save, Error : ", e);
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(MessageResponse.error(null, null));
        }
    }
}
