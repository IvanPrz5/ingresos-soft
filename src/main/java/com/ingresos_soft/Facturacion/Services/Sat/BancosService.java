package com.ingresos_soft.Facturacion.Services.Sat;

import java.util.List;
import java.util.NoSuchElementException;

import com.ingresos_soft.Facturacion.Models.Sat.BancosModel;
import com.ingresos_soft.Facturacion.Repositories.Sat.BancosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BancosService {

    @Autowired
    BancosRepository bancosRepository;

    public BancosModel get(Long id) {
        return bancosRepository.findById(id).get();
    }

    public BancosModel findById(Long id) {
        try {
            return this.get(id);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: BancosService, Method: Save, Error : ", e);
            throw new NoSuchElementException();
        }
    }

    public List<BancosModel> findAll() {
        try {
            return bancosRepository.findByStatus(true);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: BancosService, Method: Save, Error : ", e);
            throw new NoSuchElementException();
        }
    }

    public BancosModel saveOrUpdate(BancosModel request) {
        try {
            BancosModel bancosInstance;
            if (request.getId() != null) {
                bancosInstance = this.get(request.getId());
                bancosInstance.setStatus(request.getStatus());
            } else {
                bancosInstance = new BancosModel();
                bancosInstance.setStatus(true);
            }
            bancosInstance.setClave(request.getClave());
            bancosInstance.setDescripcion(request.getDescripcion());

            return bancosRepository.save(bancosInstance);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: BancosService, Method: SaveOrUpdate, Error : ", e);
            throw new IllegalArgumentException();
        }
    }
}
