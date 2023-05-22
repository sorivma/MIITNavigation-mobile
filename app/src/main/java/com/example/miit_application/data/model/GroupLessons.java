package com.example.miit_application.data.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"group_id","lesson_id"})
public class GroupLessons {

    @NonNull
    @ColumnInfo(name = "lesson_id")
    private Long lessonId;
    @NonNull
    @ColumnInfo(name = "group_id")
    private Long groupId;

    public GroupLessons(@NonNull Long lessonId, @NonNull Long groupId) {
        this.lessonId = lessonId;
        this.groupId = groupId;
    }

    @NonNull
    public Long getLessonId() {
        return lessonId;
    }

    @NonNull
    public Long getGroupId() {
        return groupId;
    }

    public void setLessonId(@NonNull Long lessonId) {
        this.lessonId = lessonId;
    }

    public void setGroupId(@NonNull Long groupId) {
        this.groupId = groupId;
    }
}
