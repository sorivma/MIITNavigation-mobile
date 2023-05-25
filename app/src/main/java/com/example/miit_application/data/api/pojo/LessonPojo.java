package com.example.miit_application.data.api.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LessonPojo {
    @SerializedName("header")
    @Expose
    String day;

    @SerializedName("isEven")
    @Expose
    boolean isEven;
    @SerializedName("type")
    @Expose
    String type;

    TeacherPojo teacher;

    SubjectPojo subject;

    TimePojo time;

    AuditoriumPojo auditorium;

    public LessonPojo(String day, boolean isEven, String type, TeacherPojo teacher, SubjectPojo subject, TimePojo time, AuditoriumPojo auditorium) {
        this.day = day;
        this.isEven = isEven;
        this.type = type;
        this.teacher = teacher;
        this.subject = subject;
        this.time = time;
        this.auditorium = auditorium;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public boolean isEven() {
        return isEven;
    }

    public void setEven(boolean even) {
        isEven = even;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TeacherPojo getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherPojo teacher) {
        this.teacher = teacher;
    }

    public SubjectPojo getSubject() {
        return subject;
    }

    public void setSubject(SubjectPojo subject) {
        this.subject = subject;
    }

    public TimePojo getTime() {
        return time;
    }

    public void setTime(TimePojo time) {
        this.time = time;
    }

    public AuditoriumPojo getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(AuditoriumPojo auditorium) {
        this.auditorium = auditorium;
    }
}
