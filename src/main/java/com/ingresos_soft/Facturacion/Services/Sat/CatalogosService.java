package com.ingresos_soft.Facturacion.Services.Sat;

import java.util.List;
import java.util.NoSuchElementException;

import com.ingresos_soft.Facturacion.Models.Sat.CatalogosModel;
import com.ingresos_soft.Facturacion.Repositories.Sat.CatalogosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CatalogosService {

    @Autowired
    CatalogosRepository catalogosRepository;

    public Long getTotalCount() {
        return catalogosRepository.count();
    }

    public CatalogosModel findById(Long id) {
        try {
            return catalogosRepository.findById(id).get();
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: CatalogosService, Method: Save, Error : ", e);
            throw new NoSuchElementException();
        }
    }

    public List<CatalogosModel> findAll() {
        try {
            return catalogosRepository.findByStatus(true);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: CatalogosService, Method: Save, Error : ", e);
            throw new NoSuchElementException();
        }
    }

    public CatalogosModel saveOrUpdate(CatalogosModel request) {
        try {
            CatalogosModel catalogosInstance;
            if (request.getId() != null) {
                catalogosInstance = catalogosRepository.findById(request.getId()).get();
                catalogosInstance.setStatus(request.getStatus());
            } else {
                catalogosInstance = new CatalogosModel();
                catalogosInstance.setStatus(true);
            }
            catalogosInstance.setClave(request.getClave());
            catalogosInstance.setDescripcion(request.getDescripcion());
            return catalogosRepository.save(catalogosInstance);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: CatalogosService, Method: Save, Error : ", e);
            throw new IllegalArgumentException();
        }
    }
}
