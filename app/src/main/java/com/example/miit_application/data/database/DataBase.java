package com.example.miit_application.data.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.miit_application.data.database.dao.GroupDao;
import com.example.miit_application.data.database.dao.LessonsDao;
import com.example.miit_application.data.database.dao.NewsDao;
import com.example.miit_application.data.model.Day;
import com.example.miit_application.data.model.Group;
import com.example.miit_application.data.model.GroupLessons;
import com.example.miit_application.data.model.Lesson;
import com.example.miit_application.data.model.News;

@Database(entities = {News.class, Lesson.class, Day.class, Group.class, GroupLessons.class}, version = 1, exportSchema = false)
public abstract class DataBase extends RoomDatabase {
    private static final String ROOM_DB_NAME = "local_room10";
    private static DataBase dbInstance;

    public static DataBase getInstance(Context appContext){
        if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(
                            appContext.getApplicationContext(),
                            DataBase.class,
                            ROOM_DB_NAME
                    )
                    .createFromAsset("database/news.db")
                    .build();
        }
        return dbInstance;
    }

    public abstract NewsDao getNewsDao();

    public abstract GroupDao getGroupDao();

    public abstract LessonsDao getLessonDao();
}
