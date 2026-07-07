package com.ingresos_soft.Facturacion.Services.Sat;

import com.ingresos_soft.Facturacion.Models.Sat.ClaveUnidadModel;
import com.ingresos_soft.Facturacion.Repositories.Sat.ClaveUnidadRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class ClaveUnidadService {

    @Autowired
    ClaveUnidadRepository claveUnidadRepository;

    public ClaveUnidadModel get(Long id) {
        return claveUnidadRepository.findById(id)
                                    .orElseThrow();
    }

    public Optional<ClaveUnidadModel> findByClave(String clave) {
        try {
            return claveUnidadRepository.findByClaveAndStatus(clave,
                                                              true);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: ClaveProdService, Method: FindByClave, Error: ",
                      e);
            throw new NoSuchElementException();
        }
    }

}
