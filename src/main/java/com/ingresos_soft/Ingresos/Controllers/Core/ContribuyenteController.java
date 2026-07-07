package com.ingresos_soft.Ingresos.Controllers.Core;

import com.ingresos_soft.Ingresos.Models.Core.ContribuyenteModel;
import com.ingresos_soft.Ingresos.Services.Core.ContribuyenteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ingresos_soft.Universal.Utils.MessageResponse;
import com.ingresos_soft.Universal.Utils.ResultObjectResponse;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1/Contribuyente")
@Slf4j
public class ContribuyenteController {

    @Autowired
    ContribuyenteService contribuyenteService;

    @GetMapping("/findById/{id}")
    public ResponseEntity<ResultObjectResponse> findById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                                 .body(MessageResponse.success(contribuyenteService.findById(id)));
        } catch (Exception e) {
            log.error("Plugin: Ingresos, Controller: ContribuyenteController, Method: FindById, Error: ",
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
                                 .body(MessageResponse.success(contribuyenteService.findAll()));
        } catch (Exception e) {
            log.error("Plugin: Ingresos, Controller: ContribuyenteController, Method: FindAll, Error: ",
                      e);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                                 .body(MessageResponse.error(null,
                                                             null));
        }
    }

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<ResultObjectResponse> saveOrUpdate(@RequestBody ContribuyenteModel request) {
        try {
            contribuyenteService.saveOrUpdate(request);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                                 .body(MessageResponse.success(null));
        } catch (Exception e) {
            log.error("Plugin: Ingresos, Controller: ContribuyenteController, Method: SaveOrUpdate, Error: ",
                      e);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                                 .body(MessageResponse.error(null,
                                                             null));
        }
    }
}
