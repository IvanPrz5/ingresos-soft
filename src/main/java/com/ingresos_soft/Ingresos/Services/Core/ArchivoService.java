package com.ingresos_soft.Ingresos.Services.Core;

import com.ingresos_soft.Ingresos.Models.Core.ArchivoModel;
import com.ingresos_soft.Ingresos.Repositories.Core.ArchivoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class ArchivoService {

    @Autowired
    ArchivoRepository archivoRepository;

    public ArchivoModel save(ArchivoModel file) {
        try {
            return archivoRepository.save(file);
        } catch (Exception e) {
            log.error("Plugin: Universal, Service: ArchivoService, Method: Save, Error: ", e);
            throw new RuntimeException();
        }
    }

    public ArchivoModel asignaMultipartFile(MultipartFile file) {
        try {
            return new ArchivoModel(file.getBytes(), file.getContentType(), file.getOriginalFilename(),
                                    true);
        } catch (Exception e) {
            log.error("Plugin: Universal, Service: ArchivoService, Method: AsignaMultipartFile, Error: ", e);
            throw new RuntimeException();
        }
    }

}
