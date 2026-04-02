package com.ingresos_soft.Universal.Config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import jakarta.persistence.EntityManagerFactory;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = {"com.ingresos_soft.Ingresos.Repositories", "com.ingresos_soft.Facturacion.Repositories"},
        entityManagerFactoryRef = "dynamicEntityManagerFactory",
        transactionManagerRef = "dynamicTransactionManager"
)
public class DynamicDataSourceConfig {

    @Value("${spring.datasource.dynamic.url-prefix}")
    private String urlPrefix;

    @Value("${spring.datasource.dynamic.username}")
    private String username;

    @Value("${spring.datasource.dynamic.password}")
    private String password;

    @Value("${spring.datasource.dynamic.driver-class-name}")
    private String driverClassName;

    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();

        // Crear un datasource por defecto temporal apuntando a una BD del sistema para evitar
        // crear tablas en nomina_entidades
        HikariDataSource defaultDataSource = new HikariDataSource();
        defaultDataSource.setJdbcUrl(urlPrefix + "postgres");
        defaultDataSource.setUsername(username);
        defaultDataSource.setPassword(password);
        defaultDataSource.setDriverClassName(driverClassName);
        defaultDataSource.setMaximumPoolSize(1);
        defaultDataSource.setMinimumIdle(0);

        dynamicDataSource.setDefaultTargetDataSource(defaultDataSource);
        dynamicDataSource.setTargetDataSources(new HashMap<>());
        return dynamicDataSource;
    }

    @Bean(name = "dynamicEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean dynamicEntityManagerFactory(
            @Qualifier("dynamicDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.ingresos_soft.Ingresos.Models", "com.ingresos_soft.Facturacion.Models");
        em.setPersistenceUnitName("dynamic");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false); // No generar DDL automáticamente
        vendorAdapter.setShowSql(false);
        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "none"); // Desactivar creación automática
        properties.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        em.setJpaProperties(properties);

        return em;
    }

    @Bean(name = "dynamicTransactionManager")
    public PlatformTransactionManager dynamicTransactionManager(
            @Qualifier("dynamicEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    public HikariDataSource createDataSource(String databaseName) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(urlPrefix + databaseName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);

        // Reducir pool size para evitar "too many clients"
        dataSource.setMaximumPoolSize(2);
        dataSource.setMinimumIdle(0);

        // Configuraciones adicionales de Hikari para prevenir leaks
        dataSource.setConnectionTimeout(30000);  // 30 segundos
        dataSource.setIdleTimeout(300000);        // 5 minutos
        dataSource.setMaxLifetime(900000);        // 15 minutos
        // 120 segundos: suficiente para que Hibernate termine el DDL sin falso positivo de leak
        dataSource.setLeakDetectionThreshold(120000);
        dataSource.setPoolName("HikariPool-" + databaseName);

        // Auto-commit habilitado para evitar transacciones abiertas
        dataSource.setAutoCommit(true);

        // Validar conexiones antes de usarlas
        dataSource.setConnectionTestQuery("SELECT 1");

        return dataSource;
    }

    /**
     * Crea un datasource SIN pool (DriverManagerDataSource) exclusivo para operaciones DDL.
     * Así el EntityManagerFactory temporal de createTablesIfNeeded NO toca el pool de HikariCP
     * y se evita el "Apparent connection leak detected".
     */
    public DataSource createDdlDataSource(String databaseName) {
        org.springframework.jdbc.datasource.DriverManagerDataSource ds =
                new org.springframework.jdbc.datasource.DriverManagerDataSource();
        ds.setUrl(urlPrefix + databaseName);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driverClassName);
        return ds;
    }
}