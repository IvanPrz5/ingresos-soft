package com.ingresos_soft.Facturacion.Repositories.Sat;

import com.ingresos_soft.Facturacion.Models.Sat.ClaveUnidadModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClaveUnidadRepository extends JpaRepository<ClaveUnidadModel, Long> {
    Optional<ClaveUnidadModel> findByClaveAndStatus(String clave,
            Boolean status);
}
