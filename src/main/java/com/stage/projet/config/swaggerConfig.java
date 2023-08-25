package com.stage.projet.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.nio.file.Paths;

@Configuration
public class swaggerConfig {
        @Bean
        public Docket api(){
                return new Docket(DocumentationType.SWAGGER_2)
                        .select()
                        .apis(RequestHandlerSelectors.any())
                        .apis(RequestHandlerSelectors.basePackage("com.stage.projet.controller"))
                        .paths(PathSelectors.any())
                        .build();
        }
}
