package com.example.david.amelioration;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class Converters {
    @TypeConverter
    public String fromDayList(ArrayList<Day> workouts) {
        if (workouts == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Day>>() {}.getType();
        String json = gson.toJson(workouts, type);
        return json;
    }

    @TypeConverter
    public ArrayList<Day> toDaysList(String daysString) {
        if (daysString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Day>>() {}.getType();
        ArrayList<Day> daysList = gson.fromJson(daysString, type);
        return daysList;
    }
}