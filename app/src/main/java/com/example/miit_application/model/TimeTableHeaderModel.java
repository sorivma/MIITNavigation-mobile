package com.example.miit_application.model;

public class TimeTableHeaderModel extends TimeTableItem{
    private String dayName;
    private String date;

    public TimeTableHeaderModel(String dayName, String date) {
        this.dayName = dayName;
        this.date = date;
    }

    public String getDayName() {
        return dayName;
    }

    public String getDate() {
        return date;
    }

    @Override
    public int getViewType() {
        return 0;
    }
}
