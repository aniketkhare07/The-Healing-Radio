package com.thehealingradio.TheHealingRadio.DataClasses;

public class BlogData {

    String title, blog, image, date, time, key;

    public BlogData() {
    }

    public BlogData(String title, String blog, String image, String date, String time, String key) {
        this.title = title;
        this.blog = blog;
        this.image = image;
        this.date = date;
        this.time = time;
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}


