package com.ingresos_soft.Facturacion.Services.Sat;

import com.ingresos_soft.Facturacion.Models.Sat.ObjetoImpuestoModel;
import com.ingresos_soft.Facturacion.Repositories.Sat.ObjetoImpuestoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class ObjetoImpuestoService {

    @Autowired
    ObjetoImpuestoRepository objetoImpuestoRepository;

    public ObjetoImpuestoModel get(Long id) {
        return objetoImpuestoRepository.findById(id)
                                       .orElseThrow();
    }

    public ObjetoImpuestoModel findById(Long id) {
        try {
            return this.get(id);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: ObjetoImpuestoService, Method: FindById, Error: ",
                      e);
            throw new NoSuchElementException();
        }
    }

    public List<ObjetoImpuestoModel> findAll() {
        try {
            return objetoImpuestoRepository.findByStatus(true);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: ObjetoImpuestoService, Method: FindAll, Error: ",
                      e);
            throw new NoSuchElementException();
        }
    }

    public void saveOrUpdate(ObjetoImpuestoModel request) {
        try {
            ObjetoImpuestoModel objetoImpuestoInstance;

            if (request.getId() != null) {
                objetoImpuestoInstance = this.get(request.getId());
                objetoImpuestoInstance.setStatus(request.getStatus());
            } else {
                objetoImpuestoInstance = new ObjetoImpuestoModel();
                objetoImpuestoInstance.setStatus(true);
            }
            objetoImpuestoInstance.setDescripcion(request.getDescripcion());
            objetoImpuestoInstance.setClave(request.getClave());

            objetoImpuestoRepository.save(objetoImpuestoInstance);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: ObjetoImpuestoService, Method: FindAll, Error: ",
                      e);
            throw new NoSuchElementException();
        }
    }
}
