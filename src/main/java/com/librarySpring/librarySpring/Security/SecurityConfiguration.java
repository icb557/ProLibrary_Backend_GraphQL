package com.librarySpring.librarySpring.Security;

import com.librarySpring.librarySpring.Entities.Person.Enums.PersonRoles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        String roleAdmin = PersonRoles.ADMIN.getRole();
        String roleEmployee = PersonRoles.EMPLOYEE.getRole();

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authorize -> {
                    // Allow GraphQL endpoints (both POST for queries/mutations and GET for schema/introspection)
                    authorize.requestMatchers("/graphql/**").permitAll();
                    authorize.requestMatchers("/graphiql/**").permitAll(); // If you want to enable GraphiQL UI for testing

                    // request matchers below are for the REST endpoints (deprecated in favor of GraphQL)
//                    authorize.requestMatchers(HttpMethod.POST, "/login").permitAll();
//
//                    authorize.requestMatchers(HttpMethod.POST, "/person").hasRole(roleAdmin);
//                    authorize.requestMatchers(HttpMethod.GET, "/person/*").hasRole(roleAdmin);
//                    authorize.requestMatchers(HttpMethod.PUT, "/person/*").hasRole(roleAdmin);
//                    authorize.requestMatchers(HttpMethod.DELETE, "/person/*").hasRole(roleAdmin);
//                    authorize.requestMatchers(HttpMethod.GET, "/people").hasRole(roleAdmin);
//
//                    authorize.requestMatchers(HttpMethod.POST, "/book").hasRole(roleAdmin);
//                    authorize.requestMatchers(HttpMethod.GET, "/book/*").hasAnyRole(roleAdmin, roleEmployee);
//                    authorize.requestMatchers(HttpMethod.GET, "/books").hasAnyRole(roleAdmin, roleEmployee);
//                    authorize.requestMatchers(HttpMethod.PUT, "/book/*").hasRole(roleAdmin);
//                    authorize.requestMatchers(HttpMethod.DELETE, "/book/*").hasRole(roleAdmin);
//
//                    authorize.requestMatchers(HttpMethod.POST, "/author").hasRole(roleAdmin);
//                    authorize.requestMatchers(HttpMethod.GET, "/author/*").hasAnyRole(roleAdmin, roleEmployee);
//                    authorize.requestMatchers(HttpMethod.GET, "/authors").hasAnyRole(roleAdmin, roleEmployee);
//                    authorize.requestMatchers(HttpMethod.PUT, "/author/*").hasRole(roleAdmin);
//                    authorize.requestMatchers(HttpMethod.DELETE,"/author/*").hasRole(roleAdmin);

                    //must be at the bottom
                    authorize.anyRequest().authenticated();
                })
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(
                "http://localhost:4200",    // Angular frontend
                "http://localhost:8080"     // http client
        ));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
