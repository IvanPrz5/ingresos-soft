package com.ingresos_soft.Facturacion.Controllers.Factura;

import com.ingresos_soft.Facturacion.Services.Factura.FacturaService;
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

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1/Factura")
@Slf4j
public class FacturaController {

    @Autowired
    FacturaService facturaService;

    @GetMapping("/findById/{id}")
    public ResponseEntity<ResultObjectResponse> findById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                                 .body(MessageResponse.success(facturaService.findById(id)));
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Controller: FacturaController, Method: FindById, Error: ",
                      e);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                                 .body(MessageResponse.error(null,
                                                             null));
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<ResultObjectResponse> findAll() {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                                 .body(MessageResponse.success(facturaService.findAll()));
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Controller: FacturaController, Method: FindAll, Error: ",
                      e);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                                 .body(MessageResponse.error(null,
                                                             null));
        }
    }
}
