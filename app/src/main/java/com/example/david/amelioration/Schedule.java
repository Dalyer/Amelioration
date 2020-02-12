package com.example.david.amelioration;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.LinkedList;

@Entity(tableName = "schedule_table")
public class Schedule {

    @PrimaryKey
    @NonNull
    private String scheduleName;

    @NonNull
    private LinkedList<Day> workouts;

    public String getScheduleName() {
        return scheduleName;
    }

    public LinkedList<Day> getWorkouts() {
        return workouts;
    }
}
