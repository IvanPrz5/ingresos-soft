package com.ingresos_soft.Facturacion.Repositories.Sat;

import java.util.List;
import java.util.Optional;

import com.ingresos_soft.Facturacion.Models.Sat.RegimenFiscalModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegimenFiscalRepository extends JpaRepository<RegimenFiscalModel, Long> {
    List<RegimenFiscalModel> findByStatus(Boolean status);
    Optional<RegimenFiscalModel> findByClave(String clave);
}
