package com.example.miit_application.screens.news;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.miit_application.data.database.DataBase;
import com.example.miit_application.data.model.News;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {
    private final DataBase dataBase;
    private final LiveData<List<News>> newsLiveData;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        dataBase = DataBase.getInstance(application);
        newsLiveData = dataBase.getNewsDao().getNews();
    }

    public LiveData<List<News>> getNewsLiveData(){
        return newsLiveData;
    }
}