package com.ingresos_soft.Facturacion.Services.Sat;

import java.util.List;
import java.util.NoSuchElementException;

import com.ingresos_soft.Facturacion.Models.Sat.EstadosModel;
import com.ingresos_soft.Facturacion.Repositories.Sat.EstadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EstadosService {

    @Autowired
    EstadosRepository estadosRepository;

    public EstadosModel get(Long id) {
        return estadosRepository.findById(id).get();
    }

    public EstadosModel findById(Long id) {
        try {
            return this.get(id);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: EstadosService, Method: FindByIdAndStatus, Error: ", e);
            throw new NoSuchElementException();
        }
    }

    public List<EstadosModel> findAll() {
        try {
            return estadosRepository.findByStatus(true);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: EstadosService, Method: FindAllByStatus, Error: ", e);
            throw new NoSuchElementException();
        }
    }

    public EstadosModel saveOrUpdate(EstadosModel request) {
        try {
            EstadosModel estadosInstance;
            if (request.getId() != null) {
                estadosInstance = this.get(request.getId());
                estadosInstance.setStatus(request.getStatus());
            } else {
                estadosInstance = new EstadosModel();
                estadosInstance.setStatus(true);
            }
            estadosInstance.setClave(request.getClave());
            estadosInstance.setDescripcion(request.getDescripcion());
            estadosInstance.setIdPais(request.getIdPais());
            return estadosRepository.save(estadosInstance);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service, EstadoService, Method: SaveOrUpdate, Error: ", e);
            throw new IllegalArgumentException();
        }
    }
}
