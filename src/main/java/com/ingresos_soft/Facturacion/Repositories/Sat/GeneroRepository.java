package com.ingresos_soft.Facturacion.Repositories.Sat;

import java.util.List;
import java.util.Optional;

import com.ingresos_soft.Facturacion.Models.Sat.GeneroModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<GeneroModel, Long> {
    List<GeneroModel> findAllByStatus(Boolean status);

    Optional<GeneroModel> findByClave(String clave);
}
