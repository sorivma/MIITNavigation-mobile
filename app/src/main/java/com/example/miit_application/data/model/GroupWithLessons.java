package com.example.miit_application.data.model;


import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class GroupWithLessons {
    @Embedded
    private Group group;
    @Relation(
            parentColumn = "group_id",
            entity = Lesson.class,
            entityColumn = "lesson_id",
            associateBy = @Junction(
                    value = GroupLessons.class,
                    parentColumn = "group_id",
                    entityColumn = "lesson_id"
            )
    )
    private List<Lesson> lessons;

    public GroupWithLessons(Group group, List<Lesson> lessons) {
        this.group = group;
        this.lessons = lessons;
    }

    public Group getGroup() {
        return group;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
