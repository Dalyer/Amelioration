package com.example.david.amelioration;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;


public class Converters {
    @TypeConverter
    public String fromDayList(LinkedList<Day> workouts) {
        if (workouts == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<LinkedList<Day>>() {}.getType();
        String json = gson.toJson(workouts, type);
        return json;
    }

    @TypeConverter
    public LinkedList<Day> toDaysList(String daysString) {
        if (daysString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<LinkedList<Day>>() {}.getType();
        LinkedList<Day> daysList = gson.fromJson(daysString, type);
        return daysList;
    }
}