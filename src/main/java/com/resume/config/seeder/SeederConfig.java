package com.resume.config.seeder;

import com.resume.config.properties.DataMigrationProperties;
import com.resume.seeder.RoleDataSeeder;
import com.resume.seeder.UserDataSeeder;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
@DependsOn("liquibase")
public class SeederConfig {

    private final DataMigrationProperties dataMigrationProperties;

    private final RoleDataSeeder roleDataSeeder;

    private final UserDataSeeder userDataSeeder;


    @Autowired
    public SeederConfig(DataMigrationProperties dataMigrationProperties, RoleDataSeeder roleDataSeeder, UserDataSeeder userDataSeeder) {
        this.dataMigrationProperties = dataMigrationProperties;
        this.roleDataSeeder = roleDataSeeder;
        this.userDataSeeder = userDataSeeder;
    }

    @PostConstruct
    public void seed() {
        if (dataMigrationProperties.getIsDataSeedEnabled()) {
            this.seedData();
        }
    }

    private void seedData() {
        this.roleDataSeeder.seedData();
        this.userDataSeeder.seedData();
    }
}
