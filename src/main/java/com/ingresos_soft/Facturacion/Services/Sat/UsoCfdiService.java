package com.ingresos_soft.Facturacion.Services.Sat;

import com.ingresos_soft.Facturacion.Models.Sat.UsoCfdiModel;
import com.ingresos_soft.Facturacion.Repositories.Sat.UsoCfdiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class UsoCfdiService {

    @Autowired
    UsoCfdiRepository usoCfdiRepository;

    public UsoCfdiModel get(Long id) {
        return usoCfdiRepository.findById(id)
                                .orElseThrow();
    }

    public UsoCfdiModel findById(Long id) {
        try {
            return this.get(id);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: UsoCfdiService, Method: FindById, Error: ",
                      e);
            throw new NoSuchElementException();
        }
    }

    public List<UsoCfdiModel> findAll() {
        try {
            return usoCfdiRepository.findByStatus(true);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: UsoCfdiService, Method: FindById, Error: ",
                      e);
            throw new NoSuchElementException();
        }
    }

    public List<UsoCfdiModel> findAllUsoCfdiByReFiscalContrib(String regFiscal) {
        try {
            return this.findAll()
                       .stream()
                       .filter(usoCfdi -> {
                           String regimenes = usoCfdi.getRegimenFiscalReceptor();

                           if (regimenes == null || regimenes.isBlank()) {
                               return false;
                           }

                           return Arrays.stream(regimenes.split(","))
                                        .map(String::trim)
                                        .anyMatch(regFiscal::equals);
                       })
                       .toList();
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: UsoCfdiService, Method: FindAllUsoCfdiByReFiscalContrib, Error: ",
                      e);
            throw new NoSuchElementException();
        }
    }

    public void saveOrUpdate(UsoCfdiModel request) {
        try {
            UsoCfdiModel usoCfdiInstance;

            if (request.getId() != null) {
                usoCfdiInstance = this.get(request.getId());
                usoCfdiInstance.setStatus(request.getStatus());
            } else {
                usoCfdiInstance = new UsoCfdiModel();
                usoCfdiInstance.setStatus(true);
            }
            usoCfdiInstance.setClave(request.getClave());
            usoCfdiInstance.setDescripcion(request.getDescripcion());
            usoCfdiInstance.setRegimenFiscalReceptor(request.getRegimenFiscalReceptor());
            usoCfdiInstance.setFisica(request.getFisica());
            usoCfdiInstance.setMoral(request.getMoral());

            usoCfdiRepository.save(usoCfdiInstance);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: UsoCfdiService, Method: SaveOrUpdate, Error: ",
                      e);
            throw new NoSuchElementException();
        }
    }
}
