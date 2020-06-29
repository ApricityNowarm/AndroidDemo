package com.example.demo;

import androidx.room.TypeConverter;

import java.time.LocalDate;

public class Coverters {
    @TypeConverter
    public static LocalDate StringToDate(String s){
        return s == null ? null : LocalDate.parse(s);
    }
    @TypeConverter
    public static String DateToString(LocalDate date){
        return date == null ? null : date.toString();
    }
}
