package com.example.miit_application;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miit_application.model.NewsModel;

import java.time.LocalDate;
import java.util.ArrayList;

public class NewsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news,
                container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<NewsModel> newsModels = loadData();
        AdapterRV adapterRV = new AdapterRV(newsModels);
        RecyclerView recyclerView = view.findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext())
        );
        recyclerView.setAdapter(adapterRV);
    }

    private ArrayList<NewsModel> loadData() {
        ArrayList<NewsModel> newsModels = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            newsModels.add(
                    new NewsModel(
                            "Заголовк" + " " + i,
                            "Текст" + " " + i,
                            LocalDate.now().toString(),
                            "miit.ru"
                    )
            );
        }
        return newsModels;
    }
}
