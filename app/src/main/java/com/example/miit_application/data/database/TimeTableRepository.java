package com.example.miit_application.data.database;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.miit_application.data.model.Day;
import com.example.miit_application.data.model.DayWithLessons;
import com.example.miit_application.data.model.NoLessonPlaceHolder;
import com.example.miit_application.screens.timetable.TimeTableItem;
import com.example.miit_application.utils.DateUtility;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TimeTableRepository {
    private DataBase dataBase;

    public TimeTableRepository(Context context) {
        this.dataBase = DataBase.getInstance(context);
    }

    public MutableLiveData<List<TimeTableItem>> getTimeTableItems(boolean isOdd){
        List<LocalDate> dayDates;
        if (isOdd && DateUtility.isOdd(LocalDate.now())) {
             dayDates = DateUtility.findDatesInWeekFloor(LocalDate.now());
        } else {
            dayDates = DateUtility.findDatesInWeekUp(LocalDate.now());
        }
        List<DayWithLessons> dayWithLessonsList = dataBase
                .getDayDao().getDayWithLessonsByOdd(isOdd);

        List<TimeTableItem> timeTableItems = new ArrayList<>();

        if (dayWithLessonsList == null) {
            dayWithLessonsList = new ArrayList<>();
        }
        int dayNumber = 0;
        for(DayWithLessons day : dayWithLessonsList) {
            Day currentDay = day.getDay();
            currentDay.setDate(dayDates.get(dayNumber).toString());
            timeTableItems.add(currentDay);
            if (day.getLessons() == null || day.getLessons().size() == 0) {
                timeTableItems.add(new NoLessonPlaceHolder());
            } else {
                timeTableItems.addAll(day.getLessons());
            }
            dayNumber++;
        }
        return new MutableLiveData<>(timeTableItems);
    }

    public MutableLiveData<List<String>> getGroups() {
        return new MutableLiveData<>(dataBase.getGroupDao().getGroups());
    }
}
