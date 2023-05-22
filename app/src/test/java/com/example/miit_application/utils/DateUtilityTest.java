package com.example.miit_application.utils;

import junit.framework.TestCase;

import java.time.LocalDate;
import java.util.List;

public class DateUtilityTest extends TestCase {

    public void testFindDayName() {
        System.out.println(
                DateUtility.findDayName(LocalDate.now())
        );
    }

    public void testFindDayNumber() {
        System.out.println(
                DateUtility.findDayNumber(LocalDate.now())
        );
    }

    public void testFindDatesInWeek() {
        List<LocalDate> dateList = DateUtility.findDatesInWeek(LocalDate.now());
        for (LocalDate date : dateList) {
            System.out.println(date);
        }
    }
}