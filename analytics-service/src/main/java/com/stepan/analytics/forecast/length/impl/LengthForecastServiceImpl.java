package com.stepan.analytics.forecast.length.impl;

import com.stepan.analytics.forecast.BaseForecastService;
import com.stepan.analytics.forecast.TwitterMessage;
import com.stepan.analytics.forecast.composition.CompositionSubscriber;
import com.stepan.analytics.forecast.length.LengthForecastService;
import com.stepan.analytics.forecast.length.LengthSubscriber;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;

@Service
public class LengthForecastServiceImpl extends BaseForecastService implements LengthForecastService {
    public LengthForecastServiceImpl(WebClient socialWebClient) {
        super(socialWebClient);
    }

    @PostConstruct
    public void init(){
        System.out.println("Doing magic with length!!!");
        Flux<TwitterMessage> tweetData = (Flux<TwitterMessage>) this.getTweetData(TwitterMessage.class);
        tweetData.subscribe(new LengthSubscriber());

    }
}
