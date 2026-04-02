package com.ingresos_soft.Universal.Repositories;

import java.util.List;
import java.util.Optional;

import com.ingresos_soft.Universal.Models.DataSourceModel;
import com.ingresos_soft.Universal.Models.UsuariosDataSourceModel;
import com.ingresos_soft.Universal.Models.UsuariosModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosDataSourceRepository extends JpaRepository<UsuariosDataSourceModel, Long> {

    List<UsuariosDataSourceModel> findByIdUsuarioAndStatusOrderByIdDataSource(UsuariosModel idUsuario, Boolean status);

    Optional<UsuariosDataSourceModel> findByIdUsuarioAndSeleccionadaAndStatus(UsuariosModel idUsuario,
                                                                              Boolean seleccionada, Boolean status);

    Optional<UsuariosDataSourceModel> findByIdDataSourceAndIdUsuarioAndStatus(DataSourceModel idDataSource,
                                                                              UsuariosModel idUsaurio, Boolean status);

    Optional<UsuariosDataSourceModel> findByIdUsuarioAndIdDataSource_IdAndStatus(UsuariosModel idUsuario,
                                                                                 Long idDataSource,
                                                                                 Boolean status);

    List<UsuariosDataSourceModel> findByIdUsuarioAndIdDataSourceStatusAndStatusOrderByIdDataSource(
            UsuariosModel usuariosModel, Boolean statusDataSource, Boolean status);

}