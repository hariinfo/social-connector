package com.stepan.analytics.forecast.composition.impl;

import com.stepan.analytics.forecast.BaseForecastService;
import com.stepan.analytics.forecast.Forecastable;
import com.stepan.analytics.forecast.TwitterMessage;
import com.stepan.analytics.forecast.composition.CompositionSubscriber;
import com.stepan.analytics.forecast.composition.TweetCompositionForecastService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;

@Service
public class TweetCompositionForecastServiceImpl extends BaseForecastService implements TweetCompositionForecastService {
    public TweetCompositionForecastServiceImpl(WebClient socialWebClient) {
        super(socialWebClient);
    }

    @PostConstruct
    public void init(){
        System.out.println("Doing magic!!!");
        Flux<TwitterMessage> tweetData = (Flux<TwitterMessage>) this.getTweetData(TwitterMessage.class);
        tweetData.subscribe(new CompositionSubscriber());
    }
}
