package com.example.miit_application.utils;

import junit.framework.TestCase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConverterTest extends TestCase {
    public void testSwitch(){
        String day = "Четверг 25 мая".split(" ")[0];
        System.out.println(day);
    }

}