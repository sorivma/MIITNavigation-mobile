package com.example.miit_application.model;

public class TimeTableFooterModel extends TimeTableItem {

    private int number;
    private String startEndTiming;
    private String lessonName;
    private String auditoryNumber;
    private String lessonType;
    private String firstTeacher;
    private String secondTeacher;

    public TimeTableFooterModel(int number,
                                String startEndTiming,
                                String lessonName,
                                String auditoryNumber,
                                String lessonType,
                                String firstTeacher,
                                String secondTeacher) {
        this.number = number;
        this.startEndTiming = startEndTiming;
        this.lessonName = lessonName;
        this.auditoryNumber = auditoryNumber;
        this.lessonType = lessonType;
        this.firstTeacher = firstTeacher;
        this.secondTeacher = secondTeacher;
    }

    public int getNumber() {
        return number;
    }

    public String getLessonName() {
        return lessonName;
    }

    public String getFirstTeacher() {
        return firstTeacher;
    }

    public String getSecondTeacher() {
        return secondTeacher;
    }

    public String getStartEndTiming() {
        return startEndTiming;
    }

    public String getAuditoryNumber() {
        return auditoryNumber;
    }

    public String getLessonType() {
        return lessonType;
    }

    @Override
    public int getViewType() {
        return 1;
    }
}
