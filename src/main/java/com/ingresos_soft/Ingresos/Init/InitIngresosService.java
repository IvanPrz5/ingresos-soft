package com.ingresos_soft.Ingresos.Init;

import com.ingresos_soft.Ingresos.Models.Core.EntidadesModel;
import com.ingresos_soft.Ingresos.Repositories.Core.EntidadesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InitIngresosService {

    @Autowired
    EntidadesRepository entidadesRepository;

    public void initEntidades() {
        try {
            if (entidadesRepository.count() == 0) {
                entidadesRepository.save(new EntidadesModel("ORGANICOS ÑAVEZ OSORIO", "OÑO120726RX3", "70760",
                                                            "https://services.test.sw.com.mx",
                                                            "cristianmartinez@ceag.com.mx", "Esteban5", true, false,
                                                            true));
                entidadesRepository.save(new EntidadesModel("KERNEL INDUSTIA JUGUETERA", "KIJ0906199R1", "68000",
                                                            "https://services.test.sw.com.mx",
                                                            "cristianmartinez@ceag.com.mx", "Esteban5", false, false,
                                                            true));
            }
        } catch (Exception e) {
            log.error("Plugin: Ingresos, Init: InitIngresos, Method: InitEntidades, Message: ", e);
            throw new RuntimeException();
        }
    }

}
