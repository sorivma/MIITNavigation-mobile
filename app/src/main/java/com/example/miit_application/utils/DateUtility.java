package com.example.miit_application.utils;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DateUtility {

    public static String findDayName(LocalDate inputDate) {
        DayOfWeek dayOfWeek = inputDate.getDayOfWeek();
        return dayOfWeek.toString();
    }

    public static int findDayNumber(LocalDate inputdate) {
        DayOfWeek dayOfWeek = inputdate.getDayOfWeek();
        return dayOfWeek.getValue();
    }

    public static List<LocalDate> findDatesInWeek(LocalDate date) {
        LocalDate monday = date
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        return IntStream.range(0, 7).mapToObj(monday::plusDays).collect(Collectors.toList());
    }

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
