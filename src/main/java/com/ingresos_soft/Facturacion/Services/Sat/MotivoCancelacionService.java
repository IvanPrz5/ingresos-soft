package com.ingresos_soft.Facturacion.Services.Sat;

import java.util.List;
import java.util.NoSuchElementException;

import com.ingresos_soft.Facturacion.Models.Sat.MotivoCancelacionModel;
import com.ingresos_soft.Facturacion.Repositories.Sat.MotivoCancelacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MotivoCancelacionService {

    @Autowired
    MotivoCancelacionRepository motivoCancelacionRepository;

    public MotivoCancelacionModel get(Long id) {
        return motivoCancelacionRepository.findById(id).get();
    }

    public MotivoCancelacionModel findById(Long id) {
        try {
            return this.get(id);
        } catch (Exception e) {
            log.error("Plugin; Facturacion, Service: MotivoCancelacionService, Method: FindById", e);
            throw new NoSuchElementException();
        }
    }

    public List<MotivoCancelacionModel> findAll() {
        try {
            return motivoCancelacionRepository.findByStatus(true);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: MotivoCancelacionService, Method: FindAll", e);
            throw new NoSuchElementException();
        }
    }

    public MotivoCancelacionModel saveOrUpdate(MotivoCancelacionModel request) {
        try {
            MotivoCancelacionModel motivoCancelacionInstance;
            if (request.getId() != null) {
                motivoCancelacionInstance = this.get(request.getId());
                motivoCancelacionInstance.setStatus(request.getStatus());
            } else {
                motivoCancelacionInstance = new MotivoCancelacionModel();
                motivoCancelacionInstance.setStatus(true);
            }
            motivoCancelacionInstance.setClave(request.getClave());
            motivoCancelacionInstance.setDescripcion(request.getDescripcion());
            return motivoCancelacionRepository.save(motivoCancelacionInstance);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: MotivoCancelacionService, Method: FindAllByStatus", e);
            throw new IllegalArgumentException();
        }
    }
}
