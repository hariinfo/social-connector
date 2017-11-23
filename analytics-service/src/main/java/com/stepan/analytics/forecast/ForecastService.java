package com.stepan.analytics.forecast;

import reactor.core.publisher.Flux;

public interface ForecastService {
    <T extends Forecastable> Flux<? extends Forecastable> getTweetData(Class<T> clazz);
}
