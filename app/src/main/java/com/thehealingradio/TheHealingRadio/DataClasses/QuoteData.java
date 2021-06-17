package com.thehealingradio.TheHealingRadio.DataClasses;

public class QuoteData {

    private String quote, name;

    public QuoteData() {
    }

    public QuoteData(String name, String quote) {
        this.quote = quote;
        this.name = name;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

