package com.ingresos_soft.Ingresos.Repositories.Core;

import com.ingresos_soft.Ingresos.Models.Core.EntidadesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EntidadesRepository extends JpaRepository<EntidadesModel, Long> {
    List<EntidadesModel> findByStatus(Boolean status);

    Optional<EntidadesModel> findByRazonSocialAndStatus(String razonSocial, Boolean status);
}
