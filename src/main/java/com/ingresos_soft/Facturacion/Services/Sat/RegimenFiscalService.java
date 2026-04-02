package com.ingresos_soft.Facturacion.Services.Sat;

import java.util.List;
import java.util.NoSuchElementException;

import com.ingresos_soft.Facturacion.Models.Sat.RegimenFiscalModel;
import com.ingresos_soft.Facturacion.Repositories.Sat.RegimenFiscalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RegimenFiscalService {

    @Autowired
    RegimenFiscalRepository regimenFiscalRepository;

    public RegimenFiscalModel get(Long id) {
        return regimenFiscalRepository.findById(id).get();
    }

    public RegimenFiscalModel findById(Long id) {
        try {
            return this.get(id);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: RegimenFiscalService, Method: FindById", e);
            throw new NoSuchElementException();
        }
    }

    public List<RegimenFiscalModel> findAll() {
        try {
            return regimenFiscalRepository.findByStatus(true);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: RegimenFiscalService, Method: FindAll", e);
            throw new NoSuchElementException();
        }
    }

    public RegimenFiscalModel saveOrUpdate(RegimenFiscalModel request) {
        try {
            RegimenFiscalModel regimenFiscalInstance;
            if (request.getId() != null) {
                regimenFiscalInstance = this.get(request.getId());
                regimenFiscalInstance.setStatus(request.getStatus());
            } else {
                regimenFiscalInstance = new RegimenFiscalModel();
                regimenFiscalInstance.setStatus(true);
            }
            regimenFiscalInstance.setClave(request.getClave());
            regimenFiscalInstance.setDescripcion(request.getDescripcion());
            return regimenFiscalRepository.save(regimenFiscalInstance);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: RegimenFiscalService, Method: Save", e);
            throw new IllegalArgumentException();
        }
    }
}
