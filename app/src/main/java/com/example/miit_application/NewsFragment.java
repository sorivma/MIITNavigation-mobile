package com.example.miit_application;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miit_application.model.NewsModel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class NewsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        textView = view.findViewById(R.id.textView2);

        updateTime();
        return view;
    }

    private Handler handler = new Handler();
    private int[][] intervals = {{8, 30, 9, 50}, {10, 5, 11, 25}, {11, 40, 13, 0}, {13, 45, 15, 5}, {15, 20, 16, 40}, {16, 55, 18, 15}, {18, 30, 19, 50}, {20, 5, 21, 25}};
    private void updateTime() {

        LocalTime currentTime = LocalTime.now();

        String text = "";

        for (int i = 0; i < intervals.length; i++) {
            int startHour = intervals[i][0];
            int startMinute = intervals[i][1];
            int endHour = intervals[i][2];
            int endMinute = intervals[i][3];

            LocalTime startTime = LocalTime.of(startHour, startMinute);
            LocalTime endTime = LocalTime.of(endHour, endMinute);

            if (currentTime.isAfter(startTime) && currentTime.isBefore(endTime)) {
                text = startTime + " - " + endTime + " - " + (i + 1) + " пара";
                break;
            } else if (currentTime.equals(endTime)) {
                if (i == intervals.length - 1) {
                    text = "Занятий нет";
                } else {
                    int nextStartHour = intervals[i+1][0];
                    int nextStartMinute = intervals[i+1][1];
                    LocalTime nextStartTime = LocalTime.of(nextStartHour, nextStartMinute);
                    text = endTime + " - " + nextStartTime + " - " + "перерыв";
                }
                break;
            } else if (currentTime.isBefore(startTime)) {
                if (i == 0) {
                    text = "Занятий нет";
                } else {
                    int prevEndHour = intervals[i-1][2];
                    int prevEndMinute = intervals[i-1][3];
                    LocalTime prevEndTime = LocalTime.of(prevEndHour, prevEndMinute);
                    text = prevEndTime + " - " + startTime + " - " + "перерыв";
                }
                break;
            } else if (i == intervals.length - 1) {
                text = "Занятий нет";
                break;
            }
        }

        textView.setText(text);

        handler.postDelayed(this::updateTime, 1000); // Update every second
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

    @NonNull
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
