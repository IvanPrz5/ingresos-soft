package com.ingresos_soft.Facturacion.Repositories.Factura;

import com.ingresos_soft.Facturacion.Models.Factura.FacturaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacturaRepository extends JpaRepository<FacturaModel, Long> {
    List<FacturaModel> findByStatus(Boolean status);
}
