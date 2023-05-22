package com.example.miit_application.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.miit_application.data.model.Day;

import java.util.List;

@Dao
public interface DayDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDay(Day day);

    @Delete
    void deleteDay(Day day);

    @Query("SELECT * FROM day_table WHERE date = :date")
    LiveData<List<Day>> getCDayByDate(String date);
}
