package com.resume.config.database;

import com.resume.config.properties.DataMigrationProperties;
import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.exception.LiquibaseException;
import liquibase.ext.mongodb.database.MongoLiquibaseDatabase;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoLiquibaseConfig {
    private final DataMigrationProperties dataMigrationProperties;

    public MongoLiquibaseConfig(DataMigrationProperties dataMigrationProperties) {
        this.dataMigrationProperties = dataMigrationProperties;
    }

    @Bean
    public MongoLiquibaseDatabase mongoLiquibase() throws LiquibaseException {

        MongoLiquibaseDatabase database = (MongoLiquibaseDatabase) DatabaseFactory.getInstance().openDatabase(
                dataMigrationProperties.getMongoLiquibaseDatasourceUri(),
                dataMigrationProperties.getMongoLiquibaseDatasourceUsername(),
                dataMigrationProperties.getMongoLiquibaseDatasourcePassword(),
                null,
                new ClassLoaderResourceAccessor());

        Liquibase liquibase = new Liquibase(
                dataMigrationProperties.getMongoLiquibaseChangeLog(),
                new ClassLoaderResourceAccessor(),
                database);

        if (dataMigrationProperties.getIsMongoLiquibaseEnabled()) {
            liquibase.update("");
        }

        return database;
    }
}
