package com.ingresos_soft.Universal.Config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import jakarta.persistence.EntityManagerFactory;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.ingresos_soft.Universal.Repositories",
        entityManagerFactoryRef = "universalEntityManagerFactory",
        transactionManagerRef = "universalTransactionManager"
)
public class UniversalDataSourceConfig {

    @Primary
    @Bean(name = "universalDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.universal")
    public DataSource universalDataSource() {
        // Crear el datasource con configuración de HikariCP optimizada
        HikariDataSource dataSource = DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();

        // Asegurar que las configuraciones de HikariCP se apliquen
        // (refuerzan las del application-prod.properties)
        dataSource.setMaximumPoolSize(3);
        dataSource.setMinimumIdle(1);
        dataSource.setConnectionTimeout(20000);
        dataSource.setIdleTimeout(300000);
        dataSource.setMaxLifetime(1800000);
        dataSource.setLeakDetectionThreshold(60000);
        dataSource.setConnectionTestQuery("SELECT 1");
        dataSource.setValidationTimeout(5000);
        dataSource.setPoolName("HikariPool-Universal");

        return dataSource;
    }

    @Primary
    @Bean(name = "universalEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean universalEntityManagerFactory(
            @Qualifier("universalDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.ingresos_soft.Universal.Models");
        em.setPersistenceUnitName("universal");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(false);
        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        em.setJpaProperties(properties);

        return em;
    }

    @Primary
    @Bean(name = "universalTransactionManager")
    public PlatformTransactionManager universalTransactionManager(
            @Qualifier("universalEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}