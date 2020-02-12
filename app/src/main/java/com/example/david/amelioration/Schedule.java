package com.example.david.amelioration;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.LinkedList;

@Entity(tableName = "schedule_table")
public class Schedule {

    @PrimaryKey
    @NonNull
    private String mScheduleName;

    @NonNull
    private ArrayList<Day> workouts;     // TODO add a typeConverter

    public Schedule(@NonNull String scheduleName,@NonNull ArrayList<Day> workouts) {
        this.mScheduleName = scheduleName;
        this.workouts = workouts;
    }

    public String getScheduleName() {
        return mScheduleName;
    }

    public ArrayList<Day> getWorkouts() {
        return workouts;
    }
}
