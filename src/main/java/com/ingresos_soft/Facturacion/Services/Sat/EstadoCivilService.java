package com.ingresos_soft.Facturacion.Services.Sat;

import java.util.List;
import java.util.NoSuchElementException;

import com.ingresos_soft.Facturacion.Models.Sat.EstadoCivilModel;
import com.ingresos_soft.Facturacion.Repositories.Sat.EstadoCivilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EstadoCivilService {

    @Autowired
    EstadoCivilRepository estadoCivilRepository;

    public EstadoCivilModel get(Long id) {
        return estadoCivilRepository.findById(id).get();
    }

    public List<EstadoCivilModel> findAll() {
        try {
            return estadoCivilRepository.findAllByStatus(true);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: EstadoCivilService, Method: FindAllByStatus, Error: ", e);
            throw new NoSuchElementException();
        }
    }

    public EstadoCivilModel findById(Long id) {
        try {
            return this.get(id);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: EstadoCivilService, Method: FindByIdAndStatus, Error: ", e);
            throw new NoSuchElementException();
        }
    }

    public EstadoCivilModel saveOrUpdate(EstadoCivilModel request) {
        try {
            EstadoCivilModel estadoCivilInstance;
            if (request.getId() != null) {
                estadoCivilInstance = this.get(request.getId());
                estadoCivilInstance.setStatus(request.getStatus());
            } else {
                estadoCivilInstance = new EstadoCivilModel();
                estadoCivilInstance.setStatus(true);
            }
            estadoCivilInstance.setClave(request.getClave());
            estadoCivilInstance.setDescripcion(request.getDescripcion());
            return estadoCivilRepository.save(estadoCivilInstance);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: BancosService, Method: SaveOrUpdate, Error: ", e);
            throw new IllegalArgumentException();
        }
    }
}
