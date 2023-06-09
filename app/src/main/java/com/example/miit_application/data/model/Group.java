package com.example.miit_application.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "group_table")
public class Group {
    @PrimaryKey
    @ColumnInfo(name = "group_id")
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("groupName")
    @Expose
    private String name;
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
