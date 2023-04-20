package com.example.miit_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.miit_application.databinding.ActivityMainBinding;
import com.example.miit_application.model.NewsModel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private TextView textView;
    private Handler handler = new Handler();
    private int[][] intervals = {{8, 30, 9, 50}, {10, 5, 11, 25}, {11, 40, 13, 0}, {13, 45, 15, 5}, {15, 20, 16, 40}, {16, 55, 18, 15}, {18, 30, 19, 50}, {20, 5, 21, 25}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new NewsFragment());
        getSupportActionBar().setTitle(R.string.news);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            //Убрать switch

            switch (item.getItemId()) {
                case R.id.news:
                    replaceFragment(new NewsFragment());
                    //Переносишь все title в R.string
                    getSupportActionBar().setTitle(R.string.news);
                    break;
                case R.id.timetable:
                    replaceFragment(new TimetableFragment());
                    getSupportActionBar().setTitle(R.string.timetable);
                    break;
                case R.id.map:
                    replaceFragment(new MapFragment());
                    getSupportActionBar().setTitle(R.string.map);
                    break;
                case R.id.settings:
                    replaceFragment(new SettingsFragment());
                    getSupportActionBar().setTitle(R.string.settings);
                    break;
            }
            return true;
        });

        textView = findViewById(R.id.textView2);

        updateTime();
    }

    private void updateTime() {
//        Calendar calendar = Calendar.getInstance();
//        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
//        int currentMinute = calendar.get(Calendar.MINUTE);
//
        LocalTime currentTime = LocalTime.now();

        String text = "";

//        for (int i = 0; i < intervals.length; i++) {
//            int startHour = intervals[i][0];
//            int startMinute = intervals[i][1];
//            int endHour = intervals[i][2];
//            int endMinute = intervals[i][3];
//
//
//                if ((startHour==currentHour && currentMinute >= startMinute) || (currentHour==endHour && currentMinute < endMinute) || (currentHour > startHour && currentHour < endHour)) {
//                    text = startHour + ":" + String.format("%02d", startMinute) + " - " + endHour + ":" + String.format("%02d", endMinute) + " - " + (i + 1) + " пара";
//                    break;
//                } else if (currentHour==endHour && currentMinute >= endMinute || (intervals[i+1][1]>currentMinute && intervals[i+1][0]==currentHour)) {
//                    text = endHour + ":" + String.format("%02d", endMinute) + " - " + intervals[i+1][0] + ":" + String.format("%02d", intervals[i+1][1]) + " - " + "перерыв";
//                    break;
//                } else if (intervals[i+1][0]==currentHour && currentMinute < intervals[i+1][1]) {
//                    text = endHour + ":" + String.format("%02d", endMinute) + " - " + intervals[i+1][0] + ":" + String.format("%02d", intervals[i+1][1]) + " - " + "перерыв";
//                    break;
//                }
//
//                if ((currentHour < intervals[0][0] && currentMinute < intervals[0][1]) || (currentHour > intervals[7][0] && currentMinute > intervals[7][1])) {
//                    text = "Занятий нет";
//                    break;
//                }
//
//
//        }

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
                    int nextStartHour = intervals[i + 1][0];
                    int nextStartMinute = intervals[i + 1][1];
                    LocalTime nextStartTime = LocalTime.of(nextStartHour, nextStartMinute);
                    text = endTime + " - " + nextStartTime + " - " + "перерыв";
                }
                break;
            } else if (currentTime.isBefore(startTime)) {
                if (i == 0) {
                    text = "Занятий нет";
                } else {
                    int prevEndHour = intervals[i - 1][2];
                    int prevEndMinute = intervals[i - 1][3];
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

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}