package com.example.stockx.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Value("${application.version}")
    private String version;

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info()
                        .title("stockX")
                        .version(version)
                        .description("Bamboo-Powered API for stock trading"));
    }


    @Bean
    public GroupedOpenApi authenticationEndpoint() {
        return GroupedOpenApi
                .builder()
                .group("Getting started")
                .pathsToMatch("/api/v1/auth/**").build();
    }

    @Bean
    public GroupedOpenApi registrationEndpoint() {
        return GroupedOpenApi
                .builder()
                .group("Registration")
                .pathsToMatch("/api/v1/registration/**").build();
    }

    @Bean
    public GroupedOpenApi accountEndpoint() {
        return GroupedOpenApi
                .builder()
                .group("Stock Trading Account")
                .pathsToMatch("/api/v1/account/**").build();
    }

    @Bean
    public GroupedOpenApi onboardEndpoint() {
        return GroupedOpenApi
                .builder()
                .group("Onboard Actions")
                .pathsToMatch("/api/v1/onboard/**").build();
    }

    @Bean
    public GroupedOpenApi portfolioEndpoint() {
        return GroupedOpenApi
                .builder()
                .group("Investment Portfolio")
                .pathsToMatch("/api/v1/portfolio/**").build();
    }
    @Bean
    public GroupedOpenApi stockTradingEndpoint() {
        return GroupedOpenApi
                .builder()
                .group("Stock Trading")
                .pathsToMatch("/api/v1/stocks/**").build();
    }

    @Bean
    public GroupedOpenApi userProfileEndpoint() {
        return GroupedOpenApi
                .builder()
                .group("User Profile Management")
                .pathsToMatch("/api/v1/user/**").build();
    }

    @Bean
    public GroupedOpenApi withdrawalEndpoint() {
        return GroupedOpenApi
                .builder()
                .group("Money withdrawal")
                .pathsToMatch("/api/v1/withdraw/**").build();
    }
}

