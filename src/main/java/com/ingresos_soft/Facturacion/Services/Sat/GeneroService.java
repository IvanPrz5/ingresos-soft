package com.ingresos_soft.Facturacion.Services.Sat;

import java.util.List;
import java.util.NoSuchElementException;

import com.ingresos_soft.Facturacion.Models.Sat.GeneroModel;
import com.ingresos_soft.Facturacion.Repositories.Sat.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GeneroService {

    @Autowired
    GeneroRepository generoRepository;

    public GeneroModel get(Long id) {
        return generoRepository.findById(id).get();
    }

    public List<GeneroModel> findAll() {
        try {
            return generoRepository.findAllByStatus(true);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: generoService, Method: FindAll, Error: ", e);
            throw new NoSuchElementException();
        }
    }

    public GeneroModel findById(Long id) {
        try {
            return this.get(id);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: generoService, Method: FindById, Error: ", e);
            throw new NoSuchElementException();
        }
    }

    public GeneroModel saveOrUpdate(GeneroModel request) {
        try {
            GeneroModel generoInstance;
            if (request.getId() != null) {
                generoInstance = this.get(request.getId());
                generoInstance.setStatus(request.getStatus());
            } else {
                generoInstance = new GeneroModel();
                generoInstance.setStatus(true);
            }
            generoInstance.setClave(request.getClave());
            generoInstance.setDescripcion(request.getDescripcion());
            return generoRepository.save(generoInstance);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: generoService, Method: FindByIdAndStatus, Error: ", e);
            throw new IllegalArgumentException();
        }
    }
}
