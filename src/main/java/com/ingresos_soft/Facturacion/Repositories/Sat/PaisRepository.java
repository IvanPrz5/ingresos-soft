package com.ingresos_soft.Facturacion.Repositories.Sat;

import java.util.Optional;

import com.ingresos_soft.Facturacion.Models.Sat.PaisModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepository extends JpaRepository<PaisModel, Long> {
    Optional<PaisModel> findByClave(String clave);
}
