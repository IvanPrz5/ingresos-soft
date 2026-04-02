package com.ingresos_soft.Universal.Config;

import com.ingresos_soft.Auth.Services.UserDetailImp;
import com.ingresos_soft.Universal.Models.UsuariosDataSourceModel;
import com.ingresos_soft.Universal.Repositories.UsuariosDataSourceRepository;
import com.ingresos_soft.Universal.Services.DataSourceService;
import com.ingresos_soft.Universal.Utils.DatabaseContext;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class DatabaseContextFilter implements Filter {

    @Autowired
    private UsuariosDataSourceRepository usuariosDataSourceRepository;

    @Autowired
    private DataSourceService dataSourceService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null && authentication.isAuthenticated()
                    && authentication.getPrincipal() instanceof UserDetailImp) {

                UserDetailImp userDetails = (UserDetailImp) authentication.getPrincipal();
                String databaseName = null;

                // Primero intentar leer el header X-Entity-Id
                HttpServletRequest httpRequest = (HttpServletRequest) request;
                String entityIdHeader = httpRequest.getHeader("X-Entity-Id");

                if (entityIdHeader != null && !entityIdHeader.isEmpty()) {
                    try {
                        Long entityId = Long.parseLong(entityIdHeader);
                        // Verificar que el usuario tenga acceso a esta entidad
                        var entidadPorHeader = usuariosDataSourceRepository
                                .findByIdUsuarioAndIdDataSource_IdAndStatus(userDetails.getUser(), entityId, true);

                        if (entidadPorHeader.isPresent()) {
                            databaseName = entidadPorHeader.get().getIdDataSource().getNameDatabase();
                        }
                    } catch (NumberFormatException e) {
                        // Ignorar header inválido y usar fallback
                        System.err.println("Header X-Entity-Id con formato inválido: " + entityIdHeader);
                    }
                }

                // Si no hay header o no es válido, usar la entidad seleccionada de la BD
                if (databaseName == null) {
                    var entidadSeleccionada = usuariosDataSourceRepository
                            .findByIdUsuarioAndSeleccionadaAndStatus(userDetails.getUser(), true, true);

                    if (entidadSeleccionada.isPresent()) {
                        databaseName = entidadSeleccionada.get().getIdDataSource().getNameDatabase();
                    } else {
                        // Fallback final: usar la primera entidad activa
                        List<UsuariosDataSourceModel> usuariosEntidades = usuariosDataSourceRepository
                                .findByIdUsuarioAndStatusOrderByIdDataSource(userDetails.getUser(), true);

                        if (!usuariosEntidades.isEmpty()) {
                            databaseName = usuariosEntidades.get(0).getIdDataSource().getNameDatabase();
                        }
                    }
                }

                if (databaseName != null) {
                    // Registrar el datasource si no existe
                    dataSourceService.registerDataSource(databaseName);

                    // Establecer el contexto
                    DatabaseContext.setCurrentDatabase(databaseName);
                }
            }

            chain.doFilter(request, response);
        } finally {
            // Limpiar el contexto después de la petición
            DatabaseContext.clear();
        }
    }
}