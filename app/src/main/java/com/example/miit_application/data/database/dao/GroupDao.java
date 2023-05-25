package com.example.miit_application.data.database.dao;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.miit_application.data.model.Group;

import java.util.List;

@Dao
public interface GroupDao {
    @Insert
    void insertGroups(List<Group> groups);
    @Query("SELECT name FROM group_table")
    List<String> getGroups();

    @Query("SELECT group_id FROM group_table WHERE name=:name")
    Long findGroupId(String name);
}
