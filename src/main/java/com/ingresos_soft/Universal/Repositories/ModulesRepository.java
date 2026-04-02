package com.ingresos_soft.Universal.Repositories;

import java.util.List;
import java.util.Optional;

import com.ingresos_soft.Universal.Models.ModulesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModulesRepository extends JpaRepository<ModulesModel, Long> {
    Optional<ModulesModel> findFirstByOrderByIdAsc();

    List<ModulesModel> findAllByStatus(Boolean status);

    Optional<ModulesModel> findByNombre(String nombre);
}