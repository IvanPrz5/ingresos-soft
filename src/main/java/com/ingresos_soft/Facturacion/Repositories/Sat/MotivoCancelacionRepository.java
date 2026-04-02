package com.ingresos_soft.Facturacion.Repositories.Sat;

import java.util.List;
import java.util.Optional;

import com.ingresos_soft.Facturacion.Models.Sat.MotivoCancelacionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotivoCancelacionRepository extends JpaRepository<MotivoCancelacionModel, Long> {
    List<MotivoCancelacionModel> findByStatus(Boolean status);

    Optional<MotivoCancelacionModel> findByClave(String clave);
}
