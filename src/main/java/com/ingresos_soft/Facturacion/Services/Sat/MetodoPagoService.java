package com.ingresos_soft.Facturacion.Services.Sat;

import com.ingresos_soft.Facturacion.Models.Sat.MetodoPagoModel;
import com.ingresos_soft.Facturacion.Repositories.Sat.MetodoPagoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class MetodoPagoService {

    @Autowired
    MetodoPagoRepository metodoPagoRepository;

    public MetodoPagoModel get(Long id) {
        return metodoPagoRepository.findById(id)
                                   .orElseThrow();
    }

    public MetodoPagoModel findById(Long id) {
        try {
            return this.get(id);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: MetodoPagoService, Method: FindById, Error: ",
                      e);
            throw new NoSuchElementException();
        }
    }

    public List<MetodoPagoModel> findAll() {
        try {
            return metodoPagoRepository.findByStatus(true);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: MetodoPagoService, Method: FindAll, Error: ",
                      e);
            throw new NoSuchElementException();
        }
    }

    public MetodoPagoModel saveOrUpdate(MetodoPagoModel request) {
        try {
            MetodoPagoModel metodoPagoInstance;
            if (request.getId() != null) {
                metodoPagoInstance = this.get(request.getId());
                metodoPagoInstance.setStatus(request.getStatus());
            } else {
                metodoPagoInstance = new MetodoPagoModel();
                metodoPagoInstance.setStatus(true);
            }
            metodoPagoInstance.setClave(request.getClave());
            metodoPagoInstance.setDescripcion(request.getDescripcion());
            return metodoPagoRepository.save(metodoPagoInstance);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: MetodoPagoService, Method: SaveOrUpdate, Error: ",
                      e);
            throw new IllegalArgumentException();
        }
    }
}
