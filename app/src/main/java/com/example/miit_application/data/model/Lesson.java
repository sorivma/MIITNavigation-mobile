package com.example.miit_application.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.miit_application.screens.timetable.TimeTableItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "lesson_table"
        , foreignKeys = {
        @ForeignKey(
                entity = Day.class,
                parentColumns = "id",
                childColumns = "day_id"
        )
})
public class Lesson extends TimeTableItem {
    @Expose
    @PrimaryKey
    private Long id;
    @SerializedName("lesson_number")
    @Expose
    private int number;
    @SerializedName("lesson_timing")
    @Expose
    private String startEndTiming;
    @SerializedName("lesson_name")
    @Expose
    private String lessonName;
    @SerializedName("lesson_auditory")
    @Expose
    private String auditoryNumber;
    @SerializedName("lesson_type")
    @Expose
    private String lessonType;
    @SerializedName("first_teacher")
    @Expose
    private String firstTeacher;
    @SerializedName("second_teacher")
    @Expose
    private String secondTeacher;

    @SerializedName("day_id")
    @Expose
    @ColumnInfo(name = "day_id", index = true)
    private Long dayId;

    @SerializedName("group_id")
    @Expose
    @ColumnInfo(name = "group_id")
    private Long groupId;

    @Ignore
    public Lesson(int number,
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

    public Lesson(Long id,
                  int number,
                  String startEndTiming,
                  String lessonName,
                  String auditoryNumber,
                  String lessonType,
                  String firstTeacher,
                  String secondTeacher,
                  Long dayId,
                  Long groupId) {
        this.id = id;
        this.number = number;
        this.startEndTiming = startEndTiming;
        this.lessonName = lessonName;
        this.auditoryNumber = auditoryNumber;
        this.lessonType = lessonType;
        this.firstTeacher = firstTeacher;
        this.secondTeacher = secondTeacher;
        this.dayId = dayId;
        this.groupId = groupId;
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

    public Long getId() {
        return id;
    }

    public Long getDayId() {
        return dayId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setStartEndTiming(String startEndTiming) {
        this.startEndTiming = startEndTiming;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public void setAuditoryNumber(String auditoryNumber) {
        this.auditoryNumber = auditoryNumber;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }

    public void setFirstTeacher(String firstTeacher) {
        this.firstTeacher = firstTeacher;
    }

    public void setSecondTeacher(String secondTeacher) {
        this.secondTeacher = secondTeacher;
    }

    public void setDayId(Long dayId) {
        this.dayId = dayId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public int getViewType() {
        return 1;
    }
}
