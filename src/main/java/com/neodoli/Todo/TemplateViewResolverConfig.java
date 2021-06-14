package com.neodoli.Todo;

import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.sql.DataSource;


@Configuration
public class TemplateViewResolverConfig {

    @Bean
    public ClassLoaderTemplateResolver resolver(){
        ClassLoaderTemplateResolver templateResolver= new ClassLoaderTemplateResolver();
                templateResolver.setPrefix("/templates/");
                templateResolver.setSuffix(".html");
                templateResolver.setTemplateMode(TemplateMode.HTML);
                return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(ClassLoaderTemplateResolver resolver){
        SpringTemplateEngine engine= new SpringTemplateEngine();
        engine.setTemplateResolver(resolver);
        return  engine;
    }

    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine){
        ThymeleafViewResolver resolver=new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine);
        return  resolver;
    }

    @Bean
    public DataSource dataSource(){
       return new EmbeddedDatabaseBuilder()
               .setType(EmbeddedDatabaseType.H2)
                //.addScript("classpath:schema.sql")
                //.addScript("classpath:data.sql")
                .build();
    }

}
