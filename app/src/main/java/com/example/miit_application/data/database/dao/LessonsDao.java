package com.example.miit_application.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.miit_application.data.model.GroupWithLessons;

import java.util.List;

@Dao
public interface LessonsDao {
    @Query("SELECT * FROM group_table WHERE name = :name")
    LiveData<List<GroupWithLessons>> getLessons(String name);
}
