package com.example.miit_application.data.api;


import com.example.miit_application.data.api.pojo.LessonPojo;
import com.example.miit_application.data.model.Group;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TimeTableApi {
   @GET("groups")
   Call<List<Group>> getAllGroups();

   @GET("timetable/{groupId}")
   Call<List<LessonPojo>> getLessonsForGroup(@Path("groupId") Long groupId);
}
