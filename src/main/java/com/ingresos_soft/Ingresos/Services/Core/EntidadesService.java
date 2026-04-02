package com.ingresos_soft.Ingresos.Services.Core;

import com.ingresos_soft.Ingresos.Dto.Core.EntidadesDto;
import com.ingresos_soft.Ingresos.Models.Core.EntidadesModel;
import com.ingresos_soft.Ingresos.Repositories.Core.EntidadesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class EntidadesService {

    @Autowired
    EntidadesRepository entidadesRepository;

    @Autowired
    ArchivoService archivoService;

    public EntidadesModel get(Long id) {
        return entidadesRepository.findById(id).orElseThrow();
    }

    public EntidadesDto findById(Long id) {
        try {
            return this.convertToDto(this.get(id));
        } catch (Exception e) {
            log.error("Plugin: Universal, Service: EntidadesService, Method: FindById, Error: ", e);
            throw new NoSuchElementException();
        }
    }

    public List<EntidadesDto> findAll() {
        try {
            return entidadesRepository.findByStatus(true).stream().map(this::convertToDto).toList();
        } catch (Exception e) {
            log.error("Plugin: Universal, Service: EntidadesService, Method: FindAll, Error: ", e);
            throw new NoSuchElementException();
        }
    }

    public EntidadesModel findByRazonSocial(String raxonSocial) {
        try {
            return entidadesRepository.findByRazonSocialAndStatus(raxonSocial, true).orElseThrow();
        } catch (Exception e) {
            log.error("Plugin: Universal, Service: EntidadesService, Method: FindByRazonSocial, Error: ", e);
            throw new NoSuchElementException();
        }
    }

    public void saveOrUpdate(EntidadesModel request, MultipartFile cer, MultipartFile key, MultipartFile formato) {
        try {
            EntidadesModel entidadesInstance;

            if (request.getId() != null) {
                entidadesInstance = this.get(request.getId());
                entidadesInstance.setStatus(request.getStatus());
            } else {
                entidadesInstance = new EntidadesModel();
                entidadesInstance.setStatus(true);
            }

            entidadesInstance.setRazonSocial(request.getRazonSocial().trim());
            entidadesInstance.setRfc(request.getRfc().trim());
            entidadesInstance.setCurp(request.getCurp().trim());
            entidadesInstance.setCodigoPostal(request.getCodigoPostal().trim());
            entidadesInstance.setOrigenRecurso(request.getOrigenRecurso());

            if (request.getNumCertificado() != null) {
                entidadesInstance.setNumCertificado(request.getNumCertificado().trim());
            }
            if (request.getPasswordKey() != null) {
                entidadesInstance.setPasswordKey(request.getPasswordKey().trim());
            }
            if (request.getUrlPac() != null) {
                entidadesInstance.setUrlPac(request.getUrlPac().trim());
            }
            if (request.getUsuarioPac() != null) {
                entidadesInstance.setUsuarioPac(request.getUsuarioPac().trim());
            }
            if (request.getPasswordPac() != null) {
                entidadesInstance.setPasswordPac(request.getPasswordPac().trim());
            }
            if (cer != null && !cer.isEmpty()) {
                entidadesInstance.setCer(archivoService.save(archivoService.asignaMultipartFile(cer)));
            }
            if (key != null && !key.isEmpty()) {
                entidadesInstance.setKey(archivoService.save(archivoService.asignaMultipartFile(key)));
            }

            entidadesRepository.save(entidadesInstance);
        } catch (Exception e) {
            log.error("Plugin: Universal, Service: EntidadesService, Method: SaveOrUpdate, Error: ", e);
            throw new IllegalArgumentException();
        }
    }


    public EntidadesDto convertToDto(EntidadesModel e) {
        return new EntidadesDto(e.getId(), e.getRazonSocial(), e.getRfc(), e.getCurp(), e.getCodigoPostal(),
                                e.getAlias(), e.getUrlPac(), e.getUsuarioPac(), e.getPasswordPac(),
                                e.getNumCertificado(), e.getPasswordKey(), e.getOrigenRecurso(), e.getIsFisica(),
                                e.getStatus(), null, null, null, null, e.getIdRegimenFiscal());
    }

}
