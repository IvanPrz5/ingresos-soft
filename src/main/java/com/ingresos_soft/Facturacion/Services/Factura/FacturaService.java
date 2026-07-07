package com.ingresos_soft.Facturacion.Services.Factura;

import com.ingresos_soft.Facturacion.Models.Factura.FacturaModel;
import com.ingresos_soft.Facturacion.Repositories.Factura.FacturaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class FacturaService {

    @Autowired
    FacturaRepository facturaRepository;

    public FacturaModel get(Long id) {
        return facturaRepository.findById(id)
                                .orElseThrow();
    }

    public FacturaModel findById(Long id) {
        try {
            return this.get(id);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: FacturaService, Method: FindById, Error: ",
                      e);
            throw new NoSuchElementException();
        }
    }

    public List<FacturaModel> findAll() {
        try {
            return facturaRepository.findByStatus(true);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: FacturaService, Method: FindAll, Error: ",
                      e);
            throw new NoSuchElementException();
        }
    }
}
