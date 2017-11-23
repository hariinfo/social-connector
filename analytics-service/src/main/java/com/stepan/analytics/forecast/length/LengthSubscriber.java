package com.stepan.analytics.forecast.length;

import com.stepan.analytics.forecast.TwitterMessage;
import reactor.core.publisher.BaseSubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class LengthSubscriber extends BaseSubscriber<TwitterMessage>{

    private List<Integer> lengths = new ArrayList<>();

    @Override
    protected void hookOnNext(TwitterMessage value) {
        super.hookOnNext(value);
        lengths.add(Math.toIntExact(value.getLength()));
        int nextLength = predictNextLength();
//        System.out.println("So next value should be " + nextLength);
    }

    private int predictNextLength() {
        double predicted = lengths.stream()
                .mapToInt(value -> value)
                .average()
                .orElseGet(null);
        return (int) predicted;
    }
}
