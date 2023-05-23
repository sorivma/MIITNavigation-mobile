package com.example.miit_application.utils;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

    public static List<LocalDate> findDatesInWeekFloor(LocalDate date) {
        LocalDate monday = date
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        return IntStream.range(0, 6).mapToObj(monday::plusDays).collect(Collectors.toList());
    }

    public static List<LocalDate> findDatesInWeekUp(LocalDate date) {
        LocalDate monday = date
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        return IntStream.range(0,6).mapToObj(monday::plusDays).collect(Collectors.toList());
    }

    public static boolean isOdd(LocalDate date){
        LocalDate september1st = LocalDate.of(date.getYear(), Month.SEPTEMBER, 1);

        TemporalAdjuster adjuster = TemporalAdjusters.ofDateAdjuster(d ->
                september1st.minusYears(d.isBefore(september1st) ? 1 : 0)
                        .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)));

        return date.with(adjuster).until(date, ChronoUnit.WEEKS) % 2 == 0;
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
