package com.ingresos_soft.Facturacion.Controllers.Sat;

import com.ingresos_soft.Facturacion.Models.Sat.ClaveProdServModel;
import com.ingresos_soft.Facturacion.Services.Sat.ClaveProdServService;
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

import java.util.Optional;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1/ClaveProdServ")
@Slf4j
public class ClaveProdServController {

    @Autowired
    ClaveProdServService claveProdServService;

    @GetMapping("/findByClave/{clave}")
    public ResponseEntity<ResultObjectResponse> findByClave(@PathVariable("clave") String clave) {
        try {
            Optional<ClaveProdServModel> claveProdServ = claveProdServService.findByClave(clave);
            return ResponseEntity.status(claveProdServ.isPresent()
                                         ? HttpStatus.ACCEPTED
                                         : HttpStatus.NOT_ACCEPTABLE)
                                 .body(claveProdServ.isPresent()
                                       ? MessageResponse.success(claveProdServ)
                                       : MessageResponse.error(null,
                                                               "La clave de producto ó servicio no existe."));
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Controller: ClaveProdServController, Method: FindByClave, Error: ",
                      e);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                                 .body(MessageResponse.error(null,
                                                             null));
        }
    }

}
