package com.example.david.amelioration;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity(tableName = "schedule_table")
public class Schedule {

    @PrimaryKey
    private int Id;     // this needs to be unique

    @NonNull
    private String mScheduleName;

    @NonNull
    private LinkedList<Day> workouts;

    public Schedule(int Id, @NonNull String scheduleName,@NonNull LinkedList<Day> workouts) {
        this.mScheduleName = scheduleName;
        this.workouts = workouts;
        this.Id = Id;
    }
    // getters

    public int getId() {
        return Id;
    }

    public String getScheduleName() {
        return mScheduleName;
    }

    public LinkedList<Day> getWorkouts() {
        return workouts;
    }

    // setters
    public void addWorkout(Day workout) {
        workouts.add(workout);
    }

    public void removeWorkout(int workout){
        workouts.remove(workout);
    }

    public void updateWorkouts(LinkedList<Day> updatedWorkouts) {
        workouts = updatedWorkouts;
    }

}
