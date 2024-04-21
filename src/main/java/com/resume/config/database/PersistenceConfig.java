package com.resume.config.database;

import com.resume.config.properties.DataProperties;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.resume.repository.ums", "com.resume.repository.cms"}
)
public class PersistenceConfig {

    private final DataProperties dataProperties;

    @Autowired
    public PersistenceConfig(DataProperties dataProperties) {
        this.dataProperties = dataProperties;
    }

    private Properties getJpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("show_sql", this.dataProperties.getIsShowSql().toString());
        properties.setProperty("format_sql", this.dataProperties.getIsFormatSql().toString());
        properties.setProperty("hibernate.dialect", this.dataProperties.getDialect());
        properties.setProperty("hibernate.hbm2ddl.auto", this.dataProperties.getDdlAutoMode());

        return properties;
    }

    @Bean
    @Primary
    DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataProperties.getDriver());
        dataSource.setUrl(this.dataProperties.getUrl());
        dataSource.setUsername(this.dataProperties.getUsername());
        dataSource.setPassword(this.dataProperties.getPassword());

        return dataSource;
    }

    @Bean
    @Primary
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.resume.entity");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setJpaProperties(this.getJpaProperties());
        emf.setPersistenceUnitName(this.dataProperties.getPersistenceUnit());

        return emf;
    }

    @Bean
    @Primary
    PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(emf);

        return jpaTransactionManager;
    }
}
