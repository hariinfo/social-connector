package com.stepan.analytics.forecast.composition;

import com.stepan.analytics.forecast.TwitterMessage;
import reactor.core.publisher.BaseSubscriber;

import java.util.ArrayList;
import java.util.List;

public class CompositionSubscriber extends BaseSubscriber<TwitterMessage> {

    private List<String> compositions = new ArrayList<>();


    @Override
    protected void hookOnNext(TwitterMessage value) {
        addNewComposition(value.getMessage());
        super.hookOnNext(value);
    }

    private void addNewComposition(String message) {
        if (compositions.isEmpty()) {
            compositions.add(message);
            return;
        }
        boolean firstLetterContained = compositions.parallelStream()
                .anyMatch(s -> s.contains(message.substring(0, 1)));
        if (firstLetterContained) {
            compositions.add(message);
        }

//        System.out.println("Compositions already managed : " + compositions.size());
    }
}
