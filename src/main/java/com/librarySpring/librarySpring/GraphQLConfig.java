package com.librarySpring.librarySpring;

import com.librarySpring.librarySpring.Security.GraphQLSecurityInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import org.springframework.graphql.server.WebGraphQlInterceptor;

@Configuration
public class GraphQLConfig {
    private final GraphQLSecurityInterceptor securityInterceptor;

    public GraphQLConfig(GraphQLSecurityInterceptor securityInterceptor) {
        this.securityInterceptor = securityInterceptor;
    }

    @Bean
    public WebGraphQlInterceptor graphQlSecurityInterceptor() {
        return securityInterceptor;
    }

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> {
            // Here you can add custom scalar types or directives if needed
        };
    }
}
