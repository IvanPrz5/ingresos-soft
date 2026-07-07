package com.ingresos_soft.Facturacion.Repositories.Sat;

import com.ingresos_soft.Facturacion.Models.Sat.TipoComprobanteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoComprobanteRepository extends JpaRepository<TipoComprobanteModel, Long> {
    List<TipoComprobanteModel> findByStatus(Boolean status);
}
