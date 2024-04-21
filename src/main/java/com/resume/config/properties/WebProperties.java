package com.resume.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class WebProperties extends AppBaseProperties {
    @Value("${spring.mvc.static-path-pattern:}")
    private String staticPathPattern;

    @Value("${spring.mvc.static-path-location:}")
    private String staticPathLocation;
}
