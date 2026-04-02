package com.ingresos_soft.Universal.Repositories;

import java.util.List;
import java.util.Optional;

import com.ingresos_soft.Universal.Models.UsuariosModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<UsuariosModel, Long> {

    Optional<UsuariosModel> findFirstByOrderByIdAsc();

    Optional<UsuariosModel> findOneByEmail(String email);

    Optional<UsuariosModel> findByIdAndStatus(Long id, Boolean status);

    List<UsuariosModel> findAllByStatus(Boolean status);
}