package com.ingresos_soft.Facturacion.Repositories.Sat;

import java.util.List;

import com.ingresos_soft.Facturacion.Models.Sat.AsentamientosModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsentamientosRepository extends JpaRepository<AsentamientosModel, Long> {
    List<AsentamientosModel> findByIdCodigoPostalCodigo(String codigo);
}
