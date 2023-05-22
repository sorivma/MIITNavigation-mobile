package com.example.miit_application.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "group_table", foreignKeys = {
        @ForeignKey(
                entity = Lesson.class,
                parentColumns = "id",
                childColumns = "group_id"
        )})
public class Group {
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "group_id")
    private Long id;
    @SerializedName("group_name")
    @Expose
    private String name;

    @Ignore
    public Group(String name) {
        this.name = name;
    }

    public Group(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
