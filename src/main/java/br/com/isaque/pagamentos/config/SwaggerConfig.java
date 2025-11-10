package br.com.isaque.pagamentos.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi pagamentosApi() {
        return GroupedOpenApi.builder()
                .group("pagamentos")
                .packagesToScan("br.com.isaque.pagamentos.controller")
                .packagesToExclude("br.com.isaque.pagamentos.handler",
                        "br.com.isaque.pagamentos.exception")
                .build();
    }
}
