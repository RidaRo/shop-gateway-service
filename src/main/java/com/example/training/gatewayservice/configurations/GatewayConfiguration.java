package com.example.training.gatewayservice.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {
    private final String keeperServiceUrl;
    private final String customerServiceUrl;

    public GatewayConfiguration(@Value("${keeper.service.url}") String keeperServiceUrl,
                                @Value("${customer.service.url}") String customerServiceUrl) {
        this.keeperServiceUrl = keeperServiceUrl;
        this.customerServiceUrl = customerServiceUrl;
    }

    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(route -> route.path("/items/").uri(keeperServiceUrl))
                .route(route -> route.path("/items/").uri(customerServiceUrl))
                .build();
    }
}
