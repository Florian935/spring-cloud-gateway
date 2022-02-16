package com.florian935.gateway.config;

import com.florian935.gateway.filter.JwtFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder routeLocatorBuilder, JwtFilter jwtFilter) {

        return routeLocatorBuilder.routes()
                .route("productModule", predicateSpec ->
                        predicateSpec
                                .path("/api/v1.0/products/**")
                                .or()
                                .path("/api/v2.0/products/**")
                                .filters(gatewayFilterSpec ->
                                        gatewayFilterSpec
                                                .filter(jwtFilter.apply(new JwtFilter.Config(
                                                        "post-filter-header-product",
                                                        "post-filter-value-product"))))
                                .uri("http://localhost:8081"))
                .route("orderModule", predicateSpec ->
                        predicateSpec
                                .path("/api/v1.0/orders/**")
                                .filters(gatewayFilterSpec ->
                                        gatewayFilterSpec.filter(jwtFilter.apply(new JwtFilter.Config(
                                                "post-filter-header-order",
                                                "post-filter-value-order"))))
                                .uri("http://localhost:8082"))
                .build();
    }
}
