package com.stepan.dashboard.configuration;

import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class DashboardConfiguration {

    @Bean
    public WebClient socialWebClient() {
        WebClient webClient = WebClient
                .builder()
                .baseUrl("http://localhost:8080")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_STREAM_JSON_VALUE)
                .build();
        return webClient;
    }

    @Bean
    public JsonParser jsonParser(){
        JsonParser parser = new JacksonJsonParser();
        return parser;
    }
}
