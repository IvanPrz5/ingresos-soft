package com.ingresos_soft.Facturacion.Services.Sat;

import com.ingresos_soft.Facturacion.Models.Sat.TipoComprobanteModel;
import com.ingresos_soft.Facturacion.Repositories.Sat.TipoComprobanteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class TipoComprobanteService {

    @Autowired
    TipoComprobanteRepository tipoComprobanteRepository;

    public TipoComprobanteModel get(Long id) {
        return tipoComprobanteRepository.findById(id)
                                        .orElseThrow();
    }

    public TipoComprobanteModel findById(Long id) {
        try {
            return this.get(id);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: TipoComprobanteService, Method: FindById, Error: ",
                      e);
            throw new NoSuchElementException();
        }
    }

    public List<TipoComprobanteModel> findAll() {
        try {
            return tipoComprobanteRepository.findByStatus(true);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: TipoComprobanteService, Method: FindByStatus, Error: ",
                      e);
            throw new NoSuchElementException();
        }
    }

    public TipoComprobanteModel saveOrUpdate(TipoComprobanteModel request) {
        try {
            TipoComprobanteModel tipoComprobanteInstance;
            if (request.getId() != null) {
                tipoComprobanteInstance = this.get(request.getId());
                tipoComprobanteInstance.setStatus(request.getStatus());
            } else {
                tipoComprobanteInstance = new TipoComprobanteModel();
                tipoComprobanteInstance.setStatus(true);
            }
            tipoComprobanteInstance.setClave(request.getClave());
            tipoComprobanteInstance.setDescripcion(request.getDescripcion());
            return tipoComprobanteRepository.save(tipoComprobanteInstance);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: TipoComprobanteService, Method: SaveOrUpdate, Error: ",
                      e);
            throw new NoSuchElementException();
        }
    }

}
