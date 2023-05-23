package com.example.miit_application.screens.timetable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miit_application.R;
import com.example.miit_application.data.model.Day;
import com.example.miit_application.data.model.Lesson;

import java.util.ArrayList;
import java.util.List;

public class TimeTableRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<TimeTableItem> timeTableItems = new ArrayList<>();

    public TimeTableRVAdapter() {

    }

    public void updateNewsList(final List<TimeTableItem> newItems) {
        if (timeTableItems != null) {
            this.timeTableItems.clear();
            this.timeTableItems.addAll(newItems);
            notifyDataSetChanged();
        } else {
            this.timeTableItems = newItems;
        }

    }

    @Override
    public int getItemViewType(int position) {
        return timeTableItems.get(position).getViewType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                View headerView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_timetable_day,parent,false);
                return new HeaderViewHolder(headerView);
            case 1:
                View footerView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_timetable_class, parent, false);
                return new FooterViewHolder(footerView);
            case 2:
                View noLessonsPlaceHolder = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_timetable_no_classes,parent,false);
                return new NoLessonViewHolder(noLessonsPlaceHolder);
            default:
                throw new IllegalArgumentException("ViewType is not supported");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case 0:
                Day headerModel = (Day) timeTableItems
                        .get(position);
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
                headerViewHolder.dayName.setText(headerModel.getDayName());
                headerViewHolder.date.setText(headerModel.getDate());
                break;
            case 1:
                Lesson footerModel = (Lesson) timeTableItems
                        .get(position);
                FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
                footerViewHolder.lessonNumber.setText(
                        String.valueOf(footerModel.getNumber()));
                footerViewHolder.timing.setText(footerModel.getStartEndTiming());
                footerViewHolder.lessonName.setText(footerModel.getLessonName());
                footerViewHolder.auditory.setText(footerModel.getAuditoryNumber());
                footerViewHolder.lessonType.setText(footerModel.getLessonType());
                footerViewHolder.firstTeacher.setText(footerModel.getSecondTeacher());
                if (footerModel.getSecondTeacher() == null){
                    footerViewHolder.firstTeacher.setBackgroundColor(View.INVISIBLE);
                }
                footerViewHolder.secondTeacher.setText(footerModel.getFirstTeacher());
                break;
            case 2:
                break;
            default:
                throw new IllegalArgumentException("View type is not supported");
         }
    }

    @Override
    public int getItemCount() {
        return timeTableItems.size();
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView dayName;
        private TextView date;
        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            dayName = itemView.findViewById(R.id.day_name);
            date = itemView.findViewById(R.id.date);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        private TextView lessonNumber;
        private TextView timing;
        private TextView lessonName;
        private TextView auditory;
        private TextView lessonType;
        private TextView firstTeacher;
        private TextView secondTeacher;
        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
            lessonNumber = itemView.findViewById(R.id.lesson_number);
            timing = itemView.findViewById(R.id.lesson_timing);
            lessonName = itemView.findViewById(R.id.lesson_name);
            auditory = itemView.findViewById(R.id.auditory_number);
            lessonType = itemView.findViewById(R.id.lesson_type);
            firstTeacher = itemView.findViewById(R.id.teacher_1);
            secondTeacher = itemView.findViewById(R.id.teacher_2);
        }


    }

    private class NoLessonViewHolder extends RecyclerView.ViewHolder {
        public NoLessonViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
