package com.ingresos_soft.Facturacion.Controllers.Sat;

import com.ingresos_soft.Facturacion.Models.Sat.ClaveUnidadModel;
import com.ingresos_soft.Facturacion.Services.Sat.ClaveUnidadService;
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

import java.util.Optional;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1/ClaveUnidad")
@Slf4j
public class ClaveUnidadController {

    @Autowired
    ClaveUnidadService claveUnidadService;

    @GetMapping("/findByClave/{clave}")
    public ResponseEntity<ResultObjectResponse> findByClave(@PathVariable("clave") String clave) {
        try {
            Optional<ClaveUnidadModel> claveUnidad = claveUnidadService.findByClave(clave);
            return ResponseEntity.status(claveUnidad.isPresent()
                                         ? HttpStatus.ACCEPTED
                                         : HttpStatus.NOT_ACCEPTABLE)
                                 .body(claveUnidad.isPresent()
                                       ? MessageResponse.success(claveUnidad)
                                       : MessageResponse.error(null,
                                                               "La clave de unidad no existe"));
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Controller: ClaveUnidadController, Method: FindByClave, Error: ",
                      e);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                                 .body(MessageResponse.error(null,
                                                             null));
        }
    }

}
