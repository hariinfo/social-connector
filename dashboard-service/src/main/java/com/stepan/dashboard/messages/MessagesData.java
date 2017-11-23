package com.stepan.dashboard.messages;

import com.stepan.dashboard.model.TwitterData;
import javafx.collections.ObservableList;
import org.reactivestreams.Subscription;
import org.springframework.stereotype.Component;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.SignalType;

import java.util.Comparator;

import static javafx.collections.FXCollections.observableArrayList;

@Component
public class MessagesData extends BaseSubscriber<TwitterData> {

    private final ObservableList<TwitterData> allMessages = observableArrayList();

    public MessagesData() {
    }

    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        super.hookOnSubscribe(subscription);
    }

    @Override
    protected void hookOnNext(TwitterData value) {
        allMessages.add(new TwitterData(value.getMessage(), value.getDate()));
        reorderList(allMessages);
        super.hookOnNext(value);
    }

    private void reorderList(ObservableList<TwitterData> allMessages) {
        allMessages.sort(Comparator.comparing(TwitterData::getLenth, Comparator.reverseOrder()));
    }

    @Override
    protected void hookOnComplete() {
        System.out.println("[dashboard-data] completed.");
        super.hookOnComplete();
    }

    @Override
    protected void hookOnError(Throwable throwable) {
        System.out.println("[dashboard-data] error! " + throwable);
        throwable.printStackTrace();
        super.hookOnError(throwable);
    }

    @Override
    protected void hookOnCancel() {
        super.hookOnCancel();
    }

    @Override
    protected void hookFinally(SignalType type) {
        super.hookFinally(type);
    }

    public ObservableList<TwitterData> getMessages() {
        return allMessages;
    }
}
