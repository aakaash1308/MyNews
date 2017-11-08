package com.example.aakaashkapoor.mynews;

/**
 * Created by aakaashkapoor on 10/25/17.
 * Source Name
 * Liberal or Conservative
 * TimeStamp
 * Article Name
 */

public class Entry {

    String Username;
    String TimeStamp;
    Integer SourceNumber;
    Integer Type;
    public Entry(String username, String timeStamp, Integer sourceNumber, Integer type) {
        Username = username;
        TimeStamp = timeStamp;
        SourceNumber = sourceNumber;
        Type = type;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }

    public Integer getSourceNumber() {
        return SourceNumber;
    }

    public void setSourceNumber(Integer sourceNumber) {
        SourceNumber = sourceNumber;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }
}
