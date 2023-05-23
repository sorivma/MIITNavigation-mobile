package com.example.miit_application.data.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class DayWithLessons {
    @Embedded
    private Day day;
    @Relation(
            parentColumn = "day_id",
            entityColumn = "day_id"
    )
    List<Lesson> lessons;

    public DayWithLessons(Day day, List<Lesson> lessons) {
        this.day = day;
        this.lessons = lessons;
    }

    public Day getDay() {
        return day;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
