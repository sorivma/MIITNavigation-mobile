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

    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}