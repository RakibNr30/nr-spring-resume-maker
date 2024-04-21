package com.resume.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class DataProperties extends AppBaseProperties {
    @Value("${spring.datasource.driver:}")
    private String driver;

    @Value("${spring.datasource.url:}")
    private String url;

    @Value("${spring.datasource.username:}")
    private String username;

    @Value("${spring.datasource.password:}")
    private String password;

    @Value("${spring.data.mongodb.uri:}")
    private String mongoDbUri;

    @Value("${spring.jpa.properties.hibernate.dialect:}")
    private String dialect;

    @Value("${spring.jpa.show-sql:false}")
    private Boolean isShowSql;

    @Value("${spring.jpa.format-sql:false}")
    private Boolean isFormatSql;

    @Value("${spring.jpa.hibernate.ddl-auto:}")
    private String ddlAutoMode;

    @Value("${spring.jpa.persistence-unit:db}")
    private String persistenceUnit;
}
