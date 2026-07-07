package com.ingresos_soft.Facturacion.Services.Sat;

import com.ingresos_soft.Facturacion.Models.Sat.ClaveProdServModel;
import com.ingresos_soft.Facturacion.Repositories.Sat.ClaveProdServRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class ClaveProdServService {

    @Autowired
    ClaveProdServRepository claveProdServRepository;

    public ClaveProdServModel get(Long id) {
        return claveProdServRepository.findById(id)
                                      .orElseThrow();
    }

    public Optional<ClaveProdServModel> findByClave(String clave) {
        try {
            return claveProdServRepository.findByClaveAndStatus(clave,
                                                                true);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: ClaveProdService, Method: FindByClave, Error: ",
                      e);
            throw new NoSuchElementException();
        }
    }

}
