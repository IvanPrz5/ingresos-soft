package com.ingresos_soft.Universal.Repositories;

import java.util.List;
import java.util.Optional;

import com.ingresos_soft.Universal.Models.RolesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<RolesModel, Long> {
    Optional<RolesModel> findFirstByOrderByIdAsc();

    Optional<RolesModel> findByDescripcion(String descripcion);

    List<RolesModel> findByStatus(Boolean status);
}