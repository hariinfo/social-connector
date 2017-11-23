package com.stepan.twitter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class TwitterRouter {

    @Bean
    RouterFunction<?> routes(TwitterHandler twitterHandler) {
        return route(GET("/twitter"), twitterHandler::emitTweets)
                .andRoute(GET("/hello"), twitterHandler::helloWorld);
    }
}
