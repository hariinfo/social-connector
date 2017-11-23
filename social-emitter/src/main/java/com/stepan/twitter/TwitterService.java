package com.stepan.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

@Service
public class TwitterService {

    private final String TWEETS_FILENAME = "D:/Java/social-connector/social-emitter/tweetdata60-mins.txt";

    private Flux<TwitterMessage> twitterData = null;

    @Autowired
    private JsonParser jsonParser;

    public Flux<TwitterMessage> emitTweets() {
//        if (twitterData == null) {
            twitterData = fileMessages()
                    .map(twitterDataMapper)
//                    .zipWith(Flux.interval(Duration.ofMillis(ThreadLocalRandom.current().nextInt(100))), (tweet, timer) -> tweet)
                    .share();
//        }
        return twitterData;
    }

    public Function<String, TwitterMessage> twitterDataMapper = (line -> {
        Map<String, Object> twitterData = jsonParser.parseMap(line);
        TwitterMessage message = new TwitterMessage();
        message.setMessage((String) twitterData.get("text"));
        try {
            message.setDatePublished(new SimpleDateFormat("EEEEE MMMMM yyyy HH:mm:ss.SSSZ").parse((String)twitterData.get("created_at")));
        } catch (ParseException e) {
            message.setDatePublished(new Date());
        }
        return message;
    });

    public Flux<String> fileMessages() {
        try {
            return Flux.fromStream(Files.lines(Paths.get(TWEETS_FILENAME)));
        } catch (IOException e) {
            return Flux.error(e);
        }
    }
}
