package com.example.miit_application.screens.timetable;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miit_application.R;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TimetableFragment extends Fragment {
    private List<String> groupList;
    private TimeTableViewModel timeTableViewModel;
    private Dialog groupSelectionDialog;
    private boolean isCurWeekOdd = false;
    private String groupSelected;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private TextView timeTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View newsView = inflater.inflate(R.layout.fragment_timetable, container, false);
        timeTextView = newsView.findViewById(R.id.textView3);

        updateTime();

        return newsView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        timeTableViewModel = new ViewModelProvider(this)
                .get(TimeTableViewModel.class);

        sharedPreferences = this.getActivity()
                .getPreferences(Context.MODE_PRIVATE);


        Button weekSelector = view.findViewById(R.id.week_selector);

        final Observer<Boolean> weekObserver = aBoolean -> {
            if (!aBoolean) {
                weekSelector.setText("1 Неделя");
            } else {
                weekSelector.setText("2 Неделя");
            }
            timeTableViewModel.updateTimeTableData();
        };

        timeTableViewModel.getIsOdd().observe(getViewLifecycleOwner(), weekObserver);

        weekSelector.setOnClickListener(v -> {
            timeTableViewModel.getIsOdd().setValue(Boolean.FALSE.
                    equals(timeTableViewModel.getIsOdd().getValue()));
        });

        Button groupSelector = view.findViewById(R.id.group_selector);
        groupSelector.setText(
                sharedPreferences.getString(getString(R.string.saved_group), "Группа")
        );

        groupList = new ArrayList<>();


        RecyclerView recyclerView = view.findViewById(R.id.time_table);
        TimeTableRVAdapter adapter = new TimeTableRVAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(
                getContext()
        ));
        recyclerView.setAdapter(adapter);

        final Observer<List<TimeTableItem>> timeTableItemsObserver = adapter::updateNewsList;

        timeTableViewModel.getTimeTableLiveData().observe(
                getViewLifecycleOwner(),
                timeTableItemsObserver
        );

        groupSelector.setOnClickListener(v -> {
                    groupSelectionDialog = new Dialog(getContext());
                    groupSelectionDialog.setContentView(R.layout.dialog_searchable_spinner);
                    groupSelectionDialog.getWindow().setLayout(1000, 2000);
                    groupSelectionDialog.show();
                    EditText editText = groupSelectionDialog.findViewById(R.id.edit_text);
                    ListView listView = groupSelectionDialog.findViewById(R.id.list_view);

                    ArrayAdapter<String> groupAdapter = new ArrayAdapter<>(getContext(),
                            R.layout.dialog_item,
                            R.id.itemTextView,
                            groupList);

                    listView.setAdapter(groupAdapter);

                    final Observer<List<String>> groupsObserver = groups -> {
                        groupAdapter.clear();
                        groupAdapter.addAll(groups);
                        groupAdapter.notifyDataSetChanged();
                    };

                    timeTableViewModel.getGroupLiveData().observe(getViewLifecycleOwner(),
                            groupsObserver);
                    editText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            groupAdapter.getFilter().filter(s);
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    listView.setOnItemClickListener((parent, view1, position, id) -> {
                        groupSelector.setText(groupAdapter.getItem(position));
                        groupSelected = groupAdapter.getItem(position);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(getString(R.string.saved_group),
                                groupAdapter.getItem(position));
                        editor.apply();
                        timeTableViewModel.groupChanged(groupSelected);
                        Log.i("GROUP_SELECTED", groupSelected);
                        groupSelectionDialog.dismiss();
                    });
                }
        );
    }

    // Убираем начала и концы пар в отделльный класс LessonTime (только хранит информацию о начале,
    // конце пар). Логику переключения выносим в отдельный класс TimeController
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

        timeTextView.setText(text);

        handler.postDelayed(this::updateTime, 1000); // Update every second
    }
}