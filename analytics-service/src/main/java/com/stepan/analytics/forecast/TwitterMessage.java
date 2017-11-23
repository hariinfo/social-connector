package com.stepan.analytics.forecast;

import java.util.Date;

public class TwitterMessage extends Forecastable{

    private String message;

    private long length;

    private Date datePublished;

    public TwitterMessage(String message, Date datePublished) {
        setMessage(message);
        this.datePublished = datePublished;
    }

    public TwitterMessage() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        this.length = message.length();
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    @Override
    public String toString() {
        return "TwitterMessage{" +
                "message='" + message + '\'' +
                ", length=" + length +
                ", datePublished=" + datePublished +
                '}';
    }
}
