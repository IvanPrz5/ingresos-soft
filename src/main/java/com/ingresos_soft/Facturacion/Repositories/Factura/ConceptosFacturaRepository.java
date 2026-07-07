package com.ingresos_soft.Facturacion.Repositories.Factura;

import com.ingresos_soft.Facturacion.Models.Factura.ConceptosFacturaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConceptosFacturaRepository extends JpaRepository<ConceptosFacturaModel, Long> {
    List<ConceptosFacturaModel> findByIdFactura_Id(Long id);
}
