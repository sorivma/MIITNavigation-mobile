package com.example.miit_application.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "news_table")
public class News {
    @Expose
    @PrimaryKey
    private Long id;
    @SerializedName("news_title")
    @Expose
    private String title;
    @SerializedName("news_text")
    @Expose
    private String text;
    @SerializedName("news_source")
    @Expose
    private String source;
    @SerializedName("news_date")
    @Expose
    @ColumnInfo(name = "date")
    private String date;

    @Ignore
    public News(String title,
                String text,
                String source,
                String date) {
        this.title = title;
        this.text = text;
        this.source = source;
        this.date = date;
    }

    public News(Long id, String title, String text, String source, String date) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
