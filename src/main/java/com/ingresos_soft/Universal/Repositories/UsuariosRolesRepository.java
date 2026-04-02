package com.ingresos_soft.Universal.Repositories;

import java.util.List;
import java.util.Optional;

import com.ingresos_soft.Universal.Models.RolesModel;
import com.ingresos_soft.Universal.Models.UsuariosModel;
import com.ingresos_soft.Universal.Models.UsuariosRolesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.Tuple;

public interface UsuariosRolesRepository extends JpaRepository<UsuariosRolesModel, Long> {

    @Query(value = "SELECT roles.id, roles.descripcion, roles.status FROM roles " +
            "INNER JOIN usuariosRoles usrRoles ON usrRoles.idRol = roles.id " +
            "WHERE usrRoles.idUsuario = ?1 AND usrRoles.status = true", nativeQuery = true)
    List<Tuple> getRolesByUsuario(Long idUsuario);

    Optional<UsuariosRolesModel> findByIdUsuarioAndIdRolAndStatus(UsuariosModel idUsuario, RolesModel idRol,
                                                                  Boolean status);

    List<UsuariosRolesModel> findByIdUsuario(UsuariosModel idUsuario);
}