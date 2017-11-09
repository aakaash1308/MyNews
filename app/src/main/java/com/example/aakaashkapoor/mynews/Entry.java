package com.example.aakaashkapoor.mynews;

/**
 * Created by aakaashkapoor on 10/25/17.
 * Source Name
 * Liberal or Conservative
 * TimeStamp
 * Article Name
 */

public class Entry {

    String username;//YOLO BIATCH
    String timeStamp;
    Integer sourceNumber;
    Integer type;
    public Entry(String username, String timeStamp, Integer sourceNumber, Integer type) {
        this.username = username;
        this.timeStamp = timeStamp;
        this.sourceNumber = sourceNumber;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getSourceNumber() {
        return sourceNumber;
    }

    public void setSourceNumber(Integer sourceNumber) {
        this.sourceNumber = sourceNumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
