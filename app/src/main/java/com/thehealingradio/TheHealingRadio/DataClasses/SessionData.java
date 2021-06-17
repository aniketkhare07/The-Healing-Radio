package com.thehealingradio.TheHealingRadio.DataClasses;

public class SessionData {

    private String title, key, url, thumb, date, time;

    public SessionData() {
    }

    public SessionData(String title, String key, String url, String thumb, String date, String time) {
        this.title = title;
        this.key = key;
        this.url = url;
        this.thumb = thumb;
        this.date = date;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
