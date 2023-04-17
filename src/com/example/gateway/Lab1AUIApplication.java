package com.example.gateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class Lab1AUIApplication {

    
    public static void main(String[] args)
    {
        SpringApplication.run(Lab1AUIApplication.class, args);
    }


    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("prisons", r -> r
                        .host("localhost:8080")
                        .and()
                        .path("/api/prisons/{name}", "/api/prisons")
                        .uri("http://localhost:8081"))
                        //.uri("http://spring-app-prison:8081"))
                .route("prisoners", r -> r
                        .host("localhost:8080")
                        .and()
                        .path("/api/prisoners", "/api/prisoners/**", "/api/prisons/{name}/prisoners",
                                "/api/prisons/{name}/prisoners/**")
                        .uri("http://localhost:8082"))
                        //.uri("http://spring-app-prisoner:8082"))
                .build();
    }

    @Bean
    public CorsWebFilter corsWebFilter() {

        final CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Collections.singletonList("*"));
        corsConfig.setMaxAge(3600L);
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        corsConfig.addAllowedHeader("*");

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
