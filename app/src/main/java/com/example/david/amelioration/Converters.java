package com.example.david.amelioration;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedList;


public class Converters {
    @TypeConverter
    public static LinkedList<String> fromString(String value) {
        Type listType = new TypeToken<LinkedList<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromLinkedList(LinkedList<String> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}