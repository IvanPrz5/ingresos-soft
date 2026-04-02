package com.ingresos_soft.Facturacion.Services.Sat;

import java.util.List;
import java.util.NoSuchElementException;

import com.ingresos_soft.Facturacion.Models.Sat.FormaPagoModel;
import com.ingresos_soft.Facturacion.Repositories.Sat.FormaPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FormaPagoService {
    @Autowired
    FormaPagoRepository formaPagoRepository;

    public FormaPagoModel get(Long id) {
        return formaPagoRepository.findById(id).get();
    }

    public FormaPagoModel findById(Long id) {
        try {
            return this.get(id);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service FormaPagoService, Method: FindById, Error: ", e);
            throw new NoSuchElementException();
        }
    }

    public List<FormaPagoModel> findAll() {
        try {
            return formaPagoRepository.findByStatus(true);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: FormaPagoService, Method: FindAll, Error: ", e);
            throw new NoSuchElementException();
        }
    }

    public FormaPagoModel saveOrUpdate(FormaPagoModel request) {
        try {
            FormaPagoModel formaPagoInstance;
            if (request.getId() != null) {
                formaPagoInstance = this.get(request.getId());
                formaPagoInstance.setStatus(request.getStatus());
            } else {
                formaPagoInstance = new FormaPagoModel();
                formaPagoInstance.setStatus(true);
            }
            formaPagoInstance.setClave(request.getClave());
            formaPagoInstance.setDescripcion(request.getDescripcion());
            return formaPagoRepository.save(formaPagoInstance);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: FormaPagoService, Method: SaveOrUpdate, Error: ", e);
            throw new IllegalArgumentException();
        }
    }
}
