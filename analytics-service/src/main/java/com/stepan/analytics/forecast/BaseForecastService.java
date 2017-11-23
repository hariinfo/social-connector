package com.stepan.analytics.forecast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

public class BaseForecastService implements ForecastService {

    private Flux<? extends Forecastable> data;

    private final WebClient socialWebClient;

    @Autowired
    public BaseForecastService(WebClient socialWebClient) {
        this.socialWebClient = socialWebClient;
    }

    @Override
    public <T extends Forecastable> Flux<? extends Forecastable> getTweetData(Class<T> clazz) {
        if (data == null) {
            data = socialWebClient
                    .get()
                    .uri("/twitter")
                    .accept(MediaType.APPLICATION_STREAM_JSON)
                    .retrieve()
                    .bodyToFlux(clazz);
        }
        return data;
    }
}
