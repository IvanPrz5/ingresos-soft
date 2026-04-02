package com.ingresos_soft.Universal.Controllers;

import com.ingresos_soft.Facturacion.Init.InitFacturacionService;
import com.ingresos_soft.Ingresos.Init.InitIngresosService;
import com.ingresos_soft.Universal.Models.DataSourceModel;
import com.ingresos_soft.Universal.Repositories.DataSourceRepository;
import com.ingresos_soft.Universal.Services.DataSourceService;
import com.ingresos_soft.Universal.Utils.DatabaseContext;
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

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1/DataSource")
@Slf4j
public class DataSourceController {

    @Autowired
    DataSourceService dataSourceService;

    @Autowired
    DataSourceRepository dataSourceRepository;

    @Autowired
    InitIngresosService initIngresosService;

    @Autowired
    InitFacturacionService initFacturacionService;

    @GetMapping("/findById/{id}")
    public ResponseEntity<ResultObjectResponse> findById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(MessageResponse.success(dataSourceService.findById(id)));
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Controller: EntidadesController, Method: FindById", e);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(MessageResponse.error(null, null));
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<ResultObjectResponse> findAll() {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(MessageResponse.success(dataSourceService.findAll()));
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Controller: EntidadesController, Methods: FindAll, Error : ", e);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(MessageResponse.error(null, null));
        }
    }

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<ResultObjectResponse> saveOrUpdate(@RequestBody DataSourceModel request) {
        try {
            dataSourceService.saveOrUpdate(request);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(MessageResponse.success(null));
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Controller: EntidadesController, Method: Save, Error : ", e);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(MessageResponse.error(null, null));
        }
    }

    @PostMapping("/init-datasource/{idDataSource}")
    public ResponseEntity<?> initializeEntity(@PathVariable Long idDataSource) {
        try {
            log.info("=== Inicialización de datasource ID: {} ===", idDataSource);

            // Buscar la entidad
            DataSourceModel dataSource = dataSourceRepository.findById(idDataSource)
                    .orElseThrow(() -> new RuntimeException("Data Source no encontrada"));

            if (!dataSource.getStatus()) {
                return ResponseEntity.badRequest().body("La data source está inactiva");
            }

            String databaseName = dataSource.getNameDatabase();
            log.info("Base de datos: {}", databaseName);

            // Registrar datasource
            dataSourceService.registerDataSource(databaseName);

            // Establecer contexto
            DatabaseContext.setCurrentDatabase(databaseName);

            try {
                log.info("Inicializando datos de Ingresos...");
                initIngresosService.initEntidades();

                log.info("Inicializando datos de Facturación...");
                initFacturacionService.initCatalogos();
                initFacturacionService.initBancos();
                initFacturacionService.initMotivoCancelacion();
                initFacturacionService.initFormaPago();
                initFacturacionService.initRegimenFiscal();
                initFacturacionService.initPais();
                initFacturacionService.initEstados();
                initFacturacionService.initEstadoCivil();
                initFacturacionService.initGenero();
                initFacturacionService.initPeriodicidad();
                initFacturacionService.initUsoCfdi();

                log.info("=== Inicialización completada para: {} ===", databaseName);

                return ResponseEntity.ok().body("DataSource inicializada exitosamente: " + dataSource.getRazonSocial());

            } finally {
                DatabaseContext.clear();
            }

        } catch (Exception e) {
            log.error("Error al inicializar datasource", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al inicializar datasource  : " + e.getMessage());
        }
    }

    @GetMapping("/test-datasource/{idDataSource}")
    public ResponseEntity<?> testDataSource(@PathVariable Long idDataSource) {
        try {
            DataSourceModel dataSource = dataSourceRepository.findById(idDataSource)
                    .orElseThrow(() -> new RuntimeException("Data Source no encontrada"));

            String databaseName = dataSource.getNameDatabase();
            dataSourceService.registerDataSource(databaseName);

            return ResponseEntity.ok().body("Datasource registrado: " + databaseName);

        } catch (Exception e) {
            log.error("Error al probar datasource", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}