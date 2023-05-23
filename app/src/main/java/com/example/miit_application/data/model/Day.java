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
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "day_id")
    private Long id;
    @SerializedName("day_name")
    @Expose
    private String dayName;

    @Ignore
    private String date;

    @SerializedName("isOdd")
    @Expose
    private boolean odd;

    @Ignore
    public Day(String dayName, boolean odd) {
        this.dayName = dayName;
        this.odd = odd;
    }


    public Day(Long id, String dayName, boolean odd) {
        this.id = id;
        this.dayName = dayName;
        this.odd = odd;
    }

    public Long getId() {
        return id;
    }

    public String getDayName() {
        return dayName;
    }

    public String getDate() {
        return date;
    }

    public boolean getOdd() {
        return odd;
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

    public void setOdd(boolean odd) {
        this.odd = odd;
    }

    @Override
    public int getViewType() {
        return 0;
    }
}
