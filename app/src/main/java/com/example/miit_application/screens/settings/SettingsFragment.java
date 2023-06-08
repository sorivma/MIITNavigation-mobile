package com.example.miit_application.screens.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.miit_application.R;


public class SettingsFragment extends Fragment {
    private Button officialSiteButton;
    private Button vkButton;
    private Button navButton;
    private Button starterButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        officialSiteButton = view.findViewById(R.id.off_site_button);
        vkButton = view.findViewById(R.id.vk_id);
        navButton = view.findViewById(R.id.nav_id);
        starterButton = view.findViewById(R.id.starter_id);

        officialSiteButton.setOnClickListener(v -> openUrl("https://www.miit.ru/"));
        vkButton.setOnClickListener(v -> openUrl("https://vk.com/rutmiitvk"));
        navButton.setOnClickListener(v -> openUrl("https://navigator-rut.ru/"));
        starterButton.setOnClickListener(v -> openUrl("https://rutsp.tilda.ws/"));
    }

    public void openUrl(String url){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(
                Uri.parse(url)
        );
        startActivity(intent);
    }
}