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
    private LinkedList<Day> workouts;     // TODO add a typeConverter

    public Schedule(@NonNull String scheduleName,@NonNull LinkedList<Day> workouts) {
        this.mScheduleName = scheduleName;
        this.workouts = workouts;
    }
    // getters

    public String getScheduleName() {
        return mScheduleName;
    }

    public LinkedList<Day> getWorkouts() {
        return workouts;
    }

    // setters
    public void addWorkout(Day workout) {
        workouts.addLast(workout);
    }

    public void removeWorkout(int workout){
        workouts.remove(workout);
    }

    public void updateWorkouts(LinkedList<Day> updatedWorkouts) {
        workouts = updatedWorkouts;
    }
}
