package com.ingresos_soft.Facturacion.Repositories.Factura;

import com.ingresos_soft.Facturacion.Models.Factura.ImpuestosFacturaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImpuestosFacturaRepository extends JpaRepository<ImpuestosFacturaModel, Long> {
    List<ImpuestosFacturaModel> findByIdConceptoFactura_Id(Long idConceptoFactura);
}
