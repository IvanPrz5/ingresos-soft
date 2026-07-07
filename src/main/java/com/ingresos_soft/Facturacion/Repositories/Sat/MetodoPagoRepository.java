package com.ingresos_soft.Facturacion.Repositories.Sat;

import com.ingresos_soft.Facturacion.Models.Sat.MetodoPagoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetodoPagoRepository extends JpaRepository<MetodoPagoModel, Long> {
    List<MetodoPagoModel> findByStatus(Boolean status);
}
