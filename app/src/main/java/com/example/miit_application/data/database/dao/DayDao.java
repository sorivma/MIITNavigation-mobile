package com.example.miit_application.data.database.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.miit_application.data.model.Day;
import com.example.miit_application.data.model.DayWithLessons;

import java.util.List;

@Dao
public interface DayDao {
    @Insert
    void insertDay(Day day);
    @Transaction
    @Query("SELECT * FROM day_table WHERE odd=:isOdd")
    List<DayWithLessons> getDayWithLessonsByOdd(boolean isOdd);
    @Query("DELETE FROM day_table")
    void deleteDays();

}
