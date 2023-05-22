package com.example.miit_application.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.miit_application.screens.timetable.TimeTableItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "day_table")
public class Day extends TimeTableItem {
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "day_id")
    private Long id;
    @SerializedName("day_name")
    @Expose
    private String dayName;

    @SerializedName("day_date")
    @Expose
    @ColumnInfo(name = "date")
    private String date;

    @Ignore
    public Day(String dayName, String date) {
        this.dayName = dayName;
        this.date = date;
    }

    public Day(Long id, String dayName, String date) {
        this.id = id;
        this.dayName = dayName;
        this.date = date;
    }


    public String getDayName() {
        return dayName;
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

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public void setDate(String date) {
        this.date = date;
    }



    @Override
    public int getViewType() {
        return 0;
    }
}
