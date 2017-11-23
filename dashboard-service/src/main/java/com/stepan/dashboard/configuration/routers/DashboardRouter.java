package com.stepan.dashboard.configuration.routers;

import com.stepan.dashboard.configuration.handlers.DashboardHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DashboardRouter {

    @Bean
    RouterFunction<ServerResponse> dashboardRoutes(DashboardHandler dashboardHandler) {
        return route(GET("/tweet"), dashboardHandler::handleTweet);
    }
}
