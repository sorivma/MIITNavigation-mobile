package com.example.miit_application;

import static androidx.navigation.ui.ActivityKt.setupActionBarWithNavController;
import static androidx.navigation.ui.BottomNavigationViewKt.setupWithNavController;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yandex.mapkit.MapKitFactory;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String MAP_API_KEY = "5b0d534c-3c2b-4c4c-a218-5569d264e7bc";
        MapKitFactory.setApiKey(MAP_API_KEY);
        setContentView(R.layout.activity_main);
        setTheme(R.style.Theme_MIIT_Application);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = navHostFragment.getNavController();
        setupWithNavController(bottomNavigationView, navController);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.newsFragment, R.id.timetableFragment, R.id.mapFragment, R.id.settingsFragment).build();
        setupActionBarWithNavController(this, navController, appBarConfiguration);
    }
}