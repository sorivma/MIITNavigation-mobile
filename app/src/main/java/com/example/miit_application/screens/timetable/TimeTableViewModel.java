package com.example.miit_application.screens.timetable;

import android.app.Application;
import android.content.Context;
import android.util.TimeUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.miit_application.data.database.DataBase;
import com.example.miit_application.data.database.TimeTableRepository;
import com.example.miit_application.data.model.Day;
import com.example.miit_application.utils.DateUtility;

import java.time.LocalDate;
import java.util.List;

public class TimeTableViewModel extends AndroidViewModel {
    private MutableLiveData<Boolean> isOdd;
    private TimeTableRepository timeTableRepository;
    private MutableLiveData<List<TimeTableItem>> timeTableLiveData;

    private MutableLiveData<List<String>> groupLiveData;


    public TimeTableViewModel(@NonNull Application application) {
        super(application);
        isOdd = new MutableLiveData<>(DateUtility.isOdd(LocalDate.now()));
        this.timeTableRepository = new TimeTableRepository(application);
        this.timeTableLiveData = timeTableRepository.getTimeTableItems(Boolean.
                TRUE.equals(isOdd.getValue()));
        this.groupLiveData = timeTableRepository.getGroups();
    }
    public LiveData<List<String>> getGroupLiveData() {
        return groupLiveData;
    }

    public LiveData<List<TimeTableItem>> getTimeTableLiveData(){
        return timeTableLiveData;
    }

    public MutableLiveData<Boolean> getIsOdd() {
        return isOdd;
    }
    public void updateTimeTableData(Boolean isOdd){
        timeTableLiveData.setValue(timeTableRepository
                .getTimeTableItems(isOdd)
                .getValue());
    }
}
