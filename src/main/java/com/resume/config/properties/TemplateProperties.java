package com.resume.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class TemplateProperties extends AppBaseProperties {
    @Value("${spring.thymeleaf.cacheable:false}")
    private Boolean isThymeleafCacheable;

    @Value("${spring.thymeleaf.prefix:/templates/}")
    private String thymeleafPrefix;

    @Value("${spring.thymeleaf.suffix:.html}")
    private String thymeleafSuffix;
}
