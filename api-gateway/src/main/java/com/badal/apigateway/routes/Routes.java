package com.badal.apigateway.routes;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Routes {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p->p.path("/eureka/web")
                        .filters(f->f.setPath("/"))
                        .uri("http://localhost:8761"))
                .route(p->p.path("/eureka/**")
                        .uri("http://localhost:8761"))
                .route(p -> p
                        .path("/api/customer/**")
                        .uri("lb://customer-service"))
                .route(p->p.path("/api/account/**")
                        .uri("lb://account-service"))
                .build();
    }

}
