package com.ingresos_soft.Universal.Services;

import com.ingresos_soft.Universal.Config.DynamicDataSource;
import com.ingresos_soft.Universal.Config.DynamicDataSourceConfig;
import com.ingresos_soft.Universal.Models.DataSourceModel;
import com.ingresos_soft.Universal.Repositories.DataSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;

import jakarta.persistence.EntityManagerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class DataSourceService {

    @Autowired
    @Qualifier("dynamicDataSource")
    private DataSource dynamicDataSource;

    @Autowired
    private DynamicDataSourceConfig dynamicDataSourceConfig;

    @Autowired
    @Qualifier("dynamicEntityManagerFactory")
    private LocalContainerEntityManagerFactoryBean dynamicEntityManagerFactory;

    @Autowired
    DataSourceRepository dataSourceRepository;

    private final Map<String, DataSource> dataSourcesCache = new ConcurrentHashMap<>();

    public DataSourceModel get(Long id) {
        return dataSourceRepository.findById(id).get();
    }

    public DataSourceModel findById(Long id) {
        try {
            return this.get(id);
        } catch (Exception e) {
            log.error("Plugin: Universal, Service: DataSourceService, Method: FindById, Error : ", e);
            throw new RuntimeException();
        }
    }

    public List<DataSourceModel> findAll() {
        try {
            return dataSourceRepository.findByStatus(true);
        } catch (Exception e) {
            log.error("Plugin: Universal, Service: DataSourceService, Method: FindAll, Error : ", e);
            throw new RuntimeException();
        }
    }

    public DataSourceModel saveOrUpdate(DataSourceModel request) {
        try {
            DataSourceModel dataSourceInstance;

            if (request.getId() != null) {
                dataSourceInstance = this.get(request.getId());
                dataSourceInstance.setStatus(request.getStatus());
            } else {
                dataSourceInstance = new DataSourceModel();
                dataSourceInstance.setStatus(true);
            }

            dataSourceInstance.setRazonSocial(request.getRazonSocial());
            dataSourceInstance.setRfc(request.getRfc());
            dataSourceInstance.setNameDatabase(request.getNameDatabase());

            return dataSourceRepository.save(dataSourceInstance);
        } catch (Exception e) {
            log.error("Plugin: Universal, Service: DataSourceService, Method: SaveOrupdate, Error: ", e);
            throw new IllegalArgumentException();
        }
    }

    public synchronized void registerDataSource(String databaseName) {
        // Doble verificación dentro del bloque sincronizado para evitar race conditions
        if (dataSourcesCache.containsKey(databaseName)) {
            return;
        }

        log.info("Registrando nuevo datasource para: {}", databaseName);

        DataSource newDataSource = dynamicDataSourceConfig.createDataSource(databaseName);
        dataSourcesCache.put(databaseName, newDataSource);

        // Actualizar el mapa de datasources del DynamicDataSource
        DynamicDataSource dynamicDs = (DynamicDataSource) dynamicDataSource;
        Map<Object, Object> targetDataSources = new ConcurrentHashMap<>();
        targetDataSources.putAll(dataSourcesCache);
        dynamicDs.setTargetDataSources(targetDataSources);
        dynamicDs.afterPropertiesSet();

        // Crear las tablas usando un datasource SIN pool para no afectar HikariCP
        javax.sql.DataSource ddlDataSource = dynamicDataSourceConfig.createDdlDataSource(databaseName);
        createTablesIfNeeded(ddlDataSource, databaseName);

        log.info("Datasource registrado exitosamente para: {}", databaseName);
    }

    private void createTablesIfNeeded(DataSource dataSource, String databaseName) {
        EntityManagerFactory tempEmf = null;
        LocalContainerEntityManagerFactoryBean factoryBean = null;
        try {
            log.info("Creando esquema de tablas para: {}", databaseName);

            // Crear EntityManagerFactory temporal solo para crear el schema
            factoryBean = new LocalContainerEntityManagerFactoryBean();
            factoryBean.setDataSource(dataSource);
            factoryBean.setPackagesToScan("com.ingresos_soft.Ingresos.Models", "com.ingresos_soft.Facturacion.Models");

            HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            vendorAdapter.setGenerateDdl(true);
            factoryBean.setJpaVendorAdapter(vendorAdapter);

            Map<String, Object> properties = new HashMap<>();
            properties.put("hibernate.hbm2ddl.auto", "update");
            properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            properties.put("hibernate.show_sql", false);
            factoryBean.setJpaPropertyMap(properties);

            factoryBean.afterPropertiesSet();
            tempEmf = factoryBean.getObject();

            // Solo necesitamos que se cree la conexión para que Hibernate ejecute el DDL
            if (tempEmf != null) {
                log.info("Esquema creado/actualizado exitosamente para: {}", databaseName);
            }

        } catch (Exception e) {
            log.error("Error al crear esquema para {}: {}", databaseName, e.getMessage(), e);
        } finally {
            // Cerrar en orden correcto: primero el EMF, luego el factory bean
            if (tempEmf != null) {
                try {
                    tempEmf.close();
                    log.debug("EntityManagerFactory cerrado para: {}", databaseName);
                } catch (Exception e) {
                    log.error("Error al cerrar EntityManagerFactory: {}", e.getMessage());
                }
            }
            if (factoryBean != null) {
                try {
                    factoryBean.destroy();
                    log.debug("FactoryBean destruido para: {}", databaseName);
                } catch (Exception e) {
                    log.error("Error al destruir FactoryBean: {}", e.getMessage());
                }
            }
        }
    }

}