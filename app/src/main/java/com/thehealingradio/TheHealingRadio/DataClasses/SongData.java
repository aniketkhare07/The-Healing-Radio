package com.thehealingradio.TheHealingRadio.DataClasses;

public class SongData {


    private String title, artist ,key, url, thumb;

    public SongData() {
    }

    public SongData(String title, String artist, String key, String url, String thumb) {
        this.title = title;
        this.artist = artist;
        this.key = key;
        this.url = url;
        this.thumb = thumb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
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
}
