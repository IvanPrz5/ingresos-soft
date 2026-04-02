package com.ingresos_soft.Facturacion.Services.Sat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.ingresos_soft.Facturacion.Models.Sat.AsentamientosModel;
import com.ingresos_soft.Facturacion.Repositories.Sat.AsentamientosRepository;
import com.ingresos_soft.Facturacion.Repositories.Sat.CodigoPostalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AsentamientosService {

    @Autowired
    AsentamientosRepository asentamientosRepository;

    @Autowired
    CodigoPostalRepository codigoPostalRepository;

    public List<Map<String, Object>> findByCodigoPostal(String codigo) {
        try {
            List<AsentamientosModel> asentamientosList = asentamientosRepository
                    .findByIdCodigoPostalCodigo(codigo);
            List<Map<String, Object>> responseList = new ArrayList<>();

            for (int i = 0; i < asentamientosList.size(); i++) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", asentamientosList.get(i).getId());
                map.put("clave", asentamientosList.get(i).getClave());
                map.put("nombre", asentamientosList.get(i).getNombre());
                map.put("status", asentamientosList.get(i).getStatus());
                responseList.add(map);
            }

            return responseList;
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: AsentamientosService, Method: FindByIdCodigoPostal, Error: ", e);
            throw new NoSuchElementException();
        }
    }
}
