package com.example.aakaashkapoor.mynews;

/**
 * Created by aakaashkapoor on 10/25/17.
 * Source Name
 * Liberal or Conservative
 * TimeStamp
 * Article Name
 */

public class Entry {

    String timeStamp;
    String sourceName;
    String articleName;
    int position;
    String timeSpent;

    public Entry(String timeStamp, String sourceName, Integer position) {

        this.timeStamp = timeStamp;
        this.sourceName = sourceName;
        this.position = position;
        this.articleName = "";
        this.timeSpent = "";
    }
    public Entry(String timeStamp, String sourceName, String articleName, Integer position, String timeSpent) {

        this.timeStamp = timeStamp;
        this.sourceName = sourceName;
        this.articleName = articleName;
        this.position = position;
        this.timeSpent = timeSpent;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }


    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(String timeSpent) {
        this.timeSpent = timeSpent;
    }


}
