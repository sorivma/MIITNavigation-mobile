package com.example.miit_application.data.database.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.miit_application.data.model.Group;

import java.util.List;

@Dao
public interface GroupDao {
    @Query("SELECT * FROM group_table")
    LiveData<List<Group>> getGroups();
}
