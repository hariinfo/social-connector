package com.stepan.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class TwitterHandler {

    @Autowired
    private TwitterService twitterService;

    public Mono<ServerResponse> emitTweets(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(twitterService.emitTweets(), TwitterMessage.class);
    }

    public Mono<ServerResponse> helloWorld(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .body(Mono.just("Hello world"), String.class);
    }
}
