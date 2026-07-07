package com.ingresos_soft.Facturacion.Controllers.Sat;

import com.ingresos_soft.Facturacion.Services.Sat.UsoCfdiService;
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
@RequestMapping("/api/v1/UsoCfdi")
@Slf4j
public class UsoCfdiController {

    @Autowired
    UsoCfdiService usoCfdiService;

    @GetMapping("/findById/{id}")
    public ResponseEntity<ResultObjectResponse> findById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                                 .body(MessageResponse.success(usoCfdiService.findById(id)));
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
                                 .body(MessageResponse.success(usoCfdiService.findAll()));
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Controller: FacturaController, Method: FindAll, Error: ",
                      e);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                                 .body(MessageResponse.error(null,
                                                             null));
        }
    }

    @GetMapping("/findAllUsoCfdiByReFiscalContrib")
    public ResponseEntity<ResultObjectResponse> findAllUsoCfdiByReFiscalContrib() {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                                 .body(MessageResponse.success(usoCfdiService.findAllUsoCfdiByReFiscalContrib("616")));
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Controller: FacturaController, Method: FindAll, Error: ",
                      e);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                                 .body(MessageResponse.error(null,
                                                             null));
        }
    }
}
