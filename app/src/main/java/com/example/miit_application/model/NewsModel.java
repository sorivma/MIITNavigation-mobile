package com.example.miit_application.model;

public class NewsModel {
    private String title;
    private String text;
    private String source;
    private String date;

    public NewsModel(String title,
                     String text,
                     String source,
                     String date) {
        this.title = title;
        this.text = text;
        this.source = source;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getSource() {
        return source;
    }

    public String getDate() {
        return date;
    }
}
