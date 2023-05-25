package com.example.miit_application.utils;

import android.util.Log;

import com.example.miit_application.data.api.pojo.LessonPojo;
import com.example.miit_application.data.model.Lesson;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static List<Lesson> convertLessonPojoToLesson(List<LessonPojo> lessonPojos) {
        List<Lesson> lessons = new ArrayList<>();
        for (LessonPojo lessonPojo : lessonPojos) {
            Log.i("DEBUG", lessonPojo.toString());
            String day = lessonPojo.getDay().split(" ")[0];
            String type = lessonPojo.getType();
            boolean isEven = lessonPojo.isEven();

            String lessonName = "";

            if (lessonPojo.getSubject() != null){
                lessonName = lessonPojo.getSubject().getName();
            }

            String regex = "(?<=\\.)";

            String firstTeacher = "";
            String secondTeacher = "";
            if (lessonPojo.getTeacher() != null){
                 firstTeacher = lessonPojo.getTeacher().getNameSurname()
                        .split(regex)[0] +
                        lessonPojo.getTeacher().getNameSurname().split(regex)[1];
                if (lessonPojo.getTeacher().getNameSurname().split(regex).length < 4) {
                    secondTeacher = "no_teacher";
                } else {
                    secondTeacher = lessonPojo.getTeacher().getNameSurname().split(regex)[2] +
                            lessonPojo.getTeacher().getNameSurname().split(regex)[3];
                }
            }

            String auditoryNumber = "";
            if (lessonPojo.getAuditorium() != null){
                auditoryNumber = lessonPojo.getAuditorium().getAuditoriumNumber();
            }

            String startTiming = lessonPojo.getTime().getTimeStart();
            LocalDateTime dateTimeS = LocalDateTime.parse(startTiming);
            String endTiming = lessonPojo.getTime().getTimeEnd();
            LocalDateTime dateTimeE = LocalDateTime.parse(endTiming);
            String normalS = dateTimeS.format(DateTimeFormatter.ofPattern("HH:mm"));
            String normalE = dateTimeE.format(DateTimeFormatter.ofPattern("HH:mm"));
            String normalTiming = normalS + "-" + normalE;

            Long dayId;
            switch (day) {
                case "Понедельник":
                    if (isEven) {
                        dayId = 1L;
                    } else {
                        dayId = 7L;
                    }
                    break;
                case "Вторник":
                    if (isEven) {
                        dayId = 2L;
                    } else {
                        dayId = 8L;
                    }
                    break;
                case "Среда":
                    if (isEven) {
                        dayId = 3L;
                    } else {
                        dayId = 9L;
                    }
                    break;
                case "Четверг":
                    if (isEven) {
                        dayId = 4L;
                    } else {
                        dayId = 10L;
                    }
                    break;
                case "Пятница":
                    if (isEven) {
                        dayId = 5L;
                    } else {
                        dayId = 11L;
                    }
                    break;
                case "Суббота":
                    if (isEven) {
                        dayId = 6L;
                    } else {
                        dayId = 12L;
                    }
                    break;
                default:
                    dayId = 1L;
                    break;
            }
            int number;
            switch (normalS) {
                case "08:30":
                    number = 1;
                    break;
                case "10:05":
                    number = 2;
                    break;
                case "11:40":
                    number = 3;
                    break;
                case "13:45":
                    number = 4;
                    break;
                case "15:20":
                    number = 5;
                    break;
                case "16:55":
                    number = 6;
                    break;
                case "18:30":
                    number = 7;
                    break;
                case "20:00":
                    number = 8;
                    break;
                default:
                    Log.i("LESSON_ERROR","ERROR IN LESSON" + " " + normalS);
                    number = 0;
                    break;
            }

            Lesson lesson = new Lesson(
                    number,
                    normalTiming,
                    lessonName,
                    auditoryNumber,
                    type,
                    firstTeacher,
                    secondTeacher,
                    dayId
            );

            Log.i("Debug",lesson.toString());

            lessons.add(lesson);
        }
        return lessons;
    }
}
