package com.stepan.dashboard.model;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class TwitterData {

    private final SimpleStringProperty message = new SimpleStringProperty();

    private final SimpleLongProperty lenth = new SimpleLongProperty(0);

    private final Date date;

    public TwitterData(String message, Date date) {
        this.date = date;
        this.message.set(message);
        this.lenth.set(message.length());
    }

    public String getMessage() {
        return message.get();
    }

    public SimpleStringProperty messageProperty() {
        return message;
    }

    public void setMessage(String message) {
        this.message.set(message);
    }

    public long getLenth() {
        return lenth.get();
    }

    public SimpleLongProperty lenthProperty() {
        return lenth;
    }

    public void setLenth(long lenth) {
        this.lenth.set(lenth);
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TwitterData that = (TwitterData) o;

        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (lenth != null ? !lenth.equals(that.lenth) : that.lenth != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (lenth != null ? lenth.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
