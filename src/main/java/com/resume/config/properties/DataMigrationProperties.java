package com.resume.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class DataMigrationProperties extends AppBaseProperties {
    @Value("${spring.liquibase.change-log:}")
    private String liquibaseChangeLog;

    @Value("${spring.liquibase.enabled:false}")
    private Boolean isLiquibaseEnabled;

    @Value("${spring.mongo-liquibase.change-log:}")
    private String mongoLiquibaseChangeLog;

    @Value("${spring.mongo-liquibase.enabled:false}")
    private Boolean isMongoLiquibaseEnabled;

    @Value("${spring.mongo-liquibase.datasource-uri:false}")
    private String mongoLiquibaseDatasourceUri;

    @Value("${spring.mongo-liquibase.datasource-username:}")
    private String mongoLiquibaseDatasourceUsername;

    @Value("${spring.mongo-liquibase.datasource-password:}")
    private String mongoLiquibaseDatasourcePassword;

    @Value("${spring.data-seed.enabled:false}")
    private Boolean isDataSeedEnabled;
}
