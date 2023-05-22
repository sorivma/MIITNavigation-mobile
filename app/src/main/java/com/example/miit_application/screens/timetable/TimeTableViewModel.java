package com.example.miit_application.screens.timetable;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.miit_application.data.database.DataBase;
import com.example.miit_application.data.model.Day;
import com.example.miit_application.data.model.Group;
import com.example.miit_application.data.model.GroupWithLessons;

import java.util.ArrayList;
import java.util.List;

public class TimeTableViewModel extends AndroidViewModel {
    private final DataBase dataBase;
    private LiveData<List<Day>> daysLiveData;
    private LiveData<List<GroupWithLessons>> lessonLiveData;
    private LiveData<List<Group>> groupLiveData;


    public TimeTableViewModel(@NonNull Application application) {
        super(application);
        this.dataBase = DataBase.getInstance(application);
        this.groupLiveData = dataBase.getGroupDao().getGroups();
    }

    public LiveData<List<Day>> getDaysLiveData() {
        return daysLiveData;
    }

    public LiveData<List<GroupWithLessons>> getLessonLiveData() {
        return lessonLiveData;
    }

    public LiveData<List<Group>> getGroupLiveData() {
        return groupLiveData;
    }

    public void changeGroup(String groupName){
        this.lessonLiveData = dataBase.getLessonDao().getLessons(groupName);
    }

    public void changeWeek(){
    }
}
