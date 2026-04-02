package com.ingresos_soft.Facturacion.Repositories.Sat;

import java.util.List;
import java.util.Optional;

import com.ingresos_soft.Facturacion.Models.Sat.BancosModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BancosRepository extends JpaRepository<BancosModel, Long> {
    List<BancosModel> findByStatus(Boolean status);

    Optional<BancosModel> findByClave(String clave);
}
