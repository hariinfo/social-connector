package com.stepan.dashboard.configuration.services;

import com.stepan.dashboard.model.TwitterData;
import com.stepan.twitter.TwitterMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.function.Function;
import java.util.function.Predicate;

@Service
public class DashboardService {

    private final WebClient socialWebClient;

    private JsonParser jsonParser = null;

    private Flux<TwitterData> tweetData = null;

    private Predicate<? super TwitterMessage> shortTweetsFilter = (twitterMessage -> twitterMessage.getLength() < 10);
    private Function<? super TwitterMessage, ? extends TwitterData> toData = (twitterMessage -> new TwitterData(twitterMessage.getMessage(), twitterMessage.getDatePublished()));

    @Autowired
    public DashboardService(WebClient socialWebClient, JsonParser jsonParser) {
        this.socialWebClient = socialWebClient;
        this.jsonParser = jsonParser;
    }

    private Flux<TwitterMessage> connectTwitter() {
        return socialWebClient
                .get()
                .uri("/twitter")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(TwitterMessage.class);
    }

    public Flux<TwitterMessage> handleTweet() {
        return connectTwitter()
                .filter(shortTweetsFilter);
    }


    public Flux<TwitterData> handleTweetData() {
        if (tweetData == null) {
            tweetData = handleTweet()
                    .map(toData);
        }
        return tweetData;
    }

    public Flux<String> handleTweetTexts() {
        return handleTweetData()
                .map(TwitterData::getMessage);
    }
}
