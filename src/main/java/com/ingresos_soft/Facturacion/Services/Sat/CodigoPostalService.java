package com.ingresos_soft.Facturacion.Services.Sat;

import com.ingresos_soft.Facturacion.Repositories.Sat.CodigoPostalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CodigoPostalService {

    @Autowired
    CodigoPostalRepository codigoPostalRepository;

    @Autowired
    AsentamientosService asentamientosService;
}
