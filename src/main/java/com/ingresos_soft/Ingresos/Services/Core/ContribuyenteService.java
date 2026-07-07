package com.ingresos_soft.Ingresos.Services.Core;

import com.ingresos_soft.Ingresos.Dto.Core.ContribuyenteDto;
import com.ingresos_soft.Ingresos.Models.Core.ContribuyenteModel;
import com.ingresos_soft.Ingresos.Repositories.Core.ContribuyenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class ContribuyenteService {

    @Autowired
    ContribuyenteRepository contribuyenteRepository;

    public ContribuyenteModel get(Long id) {
        return contribuyenteRepository.findById(id)
                                      .orElseThrow();
    }

    public ContribuyenteModel findById(Long id) {
        try {
            return this.get(id);
        } catch (Exception e) {
            log.error("Plugin: Ingresos, Service: ContribuyenteService, Method: FindById, Error: ",
                      e);
            throw new NoSuchElementException();
        }
    }

    public List<ContribuyenteDto> findAll() {
        try {
            return contribuyenteRepository.findByStatusOrderById(true)
                                          .stream()
                                          .map(this::convertToDto)
                                          .toList();
        } catch (Exception e) {
            log.error("Plugin: Ingresos, Service: ContribuyenteService, Method: FindByStatus, Error: ",
                      e);
            throw new NoSuchElementException();
        }
    }

    public void saveOrUpdate(ContribuyenteModel request) {
        try {
            ContribuyenteModel contribuyenteInstace;

            if (request.getId() != null) {
                contribuyenteInstace = this.get(request.getId());
                contribuyenteInstace.setStatus(request.getStatus());
            } else {
                contribuyenteInstace = new ContribuyenteModel();
                contribuyenteInstace.setStatus(true);
            }
            contribuyenteInstace.setPersonaFisica(request.getPersonaFisica());
            contribuyenteInstace.setIdRegimenFiscal(request.getIdRegimenFiscal());
            contribuyenteInstace.setRfc(request.getRfc()
                                               .trim());

            if (request.getPersonaFisica()) {
                contribuyenteInstace.setRepresentanteLegal(false);
                contribuyenteInstace.setRazonSocial(null);
                contribuyenteInstace.setNombre(request.getNombre()
                                                      .trim());
                contribuyenteInstace.setPrimerApellido(request.getPrimerApellido()
                                                              .trim());

                if (request.getSegundoApellido() != null) {
                    contribuyenteInstace.setSegundoApellido(request.getSegundoApellido()
                                                                   .trim());
                }

                if (request.getCurp() != null) {
                    contribuyenteInstace.setCurp(request.getCurp()
                                                        .trim());
                }

                if (request.getFechaNacimiento() != null) {
                    contribuyenteInstace.setFechaNacimiento(request.getFechaNacimiento());
                }
            } else {
                contribuyenteInstace.setRazonSocial(request.getRazonSocial()
                                                           .trim());

                contribuyenteInstace.setRepresentanteLegal(request.getRepresentanteLegal());
                if (request.getRepresentanteLegal()) {
                    contribuyenteInstace.setNombre(request.getNombre()
                                                          .trim());
                    contribuyenteInstace.setPrimerApellido(request.getPrimerApellido()
                                                                  .trim());
                    if (request.getSegundoApellido() != null) {
                        contribuyenteInstace.setSegundoApellido(request.getSegundoApellido()
                                                                       .trim());
                    }
                }
            }

            contribuyenteRepository.save(contribuyenteInstace);
        } catch (Exception e) {
            log.error("Plugin: Ingresos, Service: ContribuyenteService, Method: SaveOrUpdate, Error: ",
                      e);
            throw new IllegalArgumentException();
        }
    }

    public ContribuyenteDto convertToDto(ContribuyenteModel model) {
        return new ContribuyenteDto(model.getId(),
                                    model.getPersonaFisica()
                                    ? model.getNombreCompleto()
                                    : model.getRazonSocial(),
                                    model.getFechaNacimiento(),
                                    model.getRfc(),
                                    model.getCurp(),
                                    model.getPersonaFisica(),
                                    model.getRepresentanteLegal(),
                                    model.getStatus(),
                                    model.getIdGenero(),
                                    model.getIdRegimenFiscal());
    }
}
