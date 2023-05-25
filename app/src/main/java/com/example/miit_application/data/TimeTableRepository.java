package com.example.miit_application.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.miit_application.data.api.TimeTableApi;
import com.example.miit_application.data.api.pojo.LessonPojo;
import com.example.miit_application.data.database.DataBase;
import com.example.miit_application.data.model.Day;
import com.example.miit_application.data.model.DayWithLessons;
import com.example.miit_application.data.model.Group;
import com.example.miit_application.data.model.Lesson;
import com.example.miit_application.data.model.NoLessonPlaceHolder;
import com.example.miit_application.screens.timetable.TimeTableItem;
import com.example.miit_application.utils.Converter;
import com.example.miit_application.utils.DateUtility;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TimeTableRepository {
    private DataBase dataBase;
    private Retrofit retrofit;

    public TimeTableRepository(Context context) {
        this.dataBase = DataBase.getInstance(context);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://84.201.133.141:8080/miit/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
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
                Comparator<Lesson> comparator = Comparator.comparingInt(Lesson::getNumber);
                List<Lesson> dayLessons = day.getLessons();
                dayLessons.sort(comparator);
                timeTableItems.addAll(dayLessons);
            }
            dayNumber++;
        }
        return new MutableLiveData<>(timeTableItems);
    }

    public MutableLiveData<List<String>> getGroups() {
        List<String> groups = dataBase.getGroupDao().getGroups();
        if (groups.size() == 0){
            getTimeTableAPI().getAllGroups().enqueue(new Callback<List<Group>>() {
                @Override
                public void onResponse(@NonNull Call<List<Group>> call,
                                       @NonNull Response<List<Group>> response) {
                    dataBase.getGroupDao().insertGroups(response.body());
                }

                @Override
                public void onFailure(@NonNull Call<List<Group>> call,
                                      @NonNull Throwable t) {
                    t.printStackTrace();
                }
            });
        }
        return new MutableLiveData<>(dataBase.getGroupDao().getGroups());
    }

    public TimeTableApi getTimeTableAPI(){
        return retrofit.create(TimeTableApi.class);
    }

    public void updateTimeTable(String name) {
        Long groupId = dataBase.getGroupDao().findGroupId(name);
        getTimeTableAPI()
                .getLessonsForGroup(groupId)
                .enqueue(new Callback<List<LessonPojo>>() {
                    @Override
                    public void onResponse(Call<List<LessonPojo>> call, Response<List<LessonPojo>> response) {
                        dataBase.getDayDao().deleteLessons();
                        List<LessonPojo> lessonPojos = response.body();
                        List<Lesson> lessons = Converter.convertLessonPojoToLesson(
                                lessonPojos
                        );
                        dataBase.getDayDao().insertLessons(lessons);
                    }

                    @Override
                    public void onFailure(Call<List<LessonPojo>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

    }
}
