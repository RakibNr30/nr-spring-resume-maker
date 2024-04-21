package com.resume.config.database;

import com.resume.config.properties.DataMigrationProperties;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfig {
    private final DataMigrationProperties dataMigrationProperties;

    public LiquibaseConfig(DataMigrationProperties dataMigrationProperties) {
        this.dataMigrationProperties = dataMigrationProperties;
    }

    @Bean
    SpringLiquibase liquibase(DataSource dataSource) {
        if (dataMigrationProperties.getIsLiquibaseEnabled()) {
            SpringLiquibase springLiquibase = new SpringLiquibase();
            springLiquibase.setDataSource(dataSource);
            springLiquibase.setChangeLog(dataMigrationProperties.getLiquibaseChangeLog());

            return springLiquibase;
        } else {
            return null;
        }
    }
}
