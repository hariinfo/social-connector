package com.stepan.dashboard.configuration.handlers;

import com.stepan.dashboard.configuration.services.DashboardService;
import com.stepan.twitter.TwitterMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class DashboardHandler {

    private final DashboardService dashboardService;

    @Autowired
    public DashboardHandler(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    public Mono<ServerResponse> handleTweet(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(dashboardService.handleTweet(), TwitterMessage.class);
    }
}
