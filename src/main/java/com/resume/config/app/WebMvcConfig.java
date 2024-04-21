package com.resume.config.app;

import com.resume.config.properties.TemplateProperties;
import com.resume.config.properties.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.resume")
public class WebMvcConfig implements WebMvcConfigurer {

    private final WebProperties webProperties;
    private final TemplateProperties templateProperties;

    public WebMvcConfig(WebProperties webProperties, TemplateProperties templateProperties) {
        this.webProperties = webProperties;
        this.templateProperties = templateProperties;
    }

    private Set<IDialect> getAdditionalDialects() {
        Set<IDialect> iDialects = new HashSet<>();
        iDialects.add(new SpringSecurityDialect());

        return iDialects;
    }

    private ClassLoaderTemplateResolver getClassLoaderTemplateResolver() {
        ClassLoaderTemplateResolver classLoaderTemplateResolver = new ClassLoaderTemplateResolver();
        classLoaderTemplateResolver.setPrefix(templateProperties.getThymeleafPrefix());
        classLoaderTemplateResolver.setSuffix(templateProperties.getThymeleafSuffix());
        classLoaderTemplateResolver.setCacheable(templateProperties.getIsThymeleafCacheable());

        return classLoaderTemplateResolver;
    }

    private SpringTemplateEngine getSpringTemplateEngine() {
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(this.getClassLoaderTemplateResolver());
        springTemplateEngine.setAdditionalDialects(this.getAdditionalDialects());

        return springTemplateEngine;
    }

    @Bean
    ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(this.getSpringTemplateEngine());

        return thymeleafViewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler(webProperties.getStaticPathPattern())
                .addResourceLocations(webProperties.getStaticPathLocation());
    }
}
