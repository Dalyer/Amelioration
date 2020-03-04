package com.example.david.amelioration;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.LinkedList;

@Entity(tableName = "schedule_table")
public class Schedule {

    @PrimaryKey
    private int ScheduleId;     // this needs to be unique

    @NonNull
    private String mScheduleName;

    private int daysId;         // this needs to be unique

    public Schedule(int Id, @NonNull String scheduleName, int workouts) {
        this.mScheduleName = scheduleName;
        this.daysId = workouts;
        this.ScheduleId = Id;
    }
    // getters

    public int getScheduleId() {
        return ScheduleId;
    }

    public String getScheduleName() {
        return mScheduleName;
    }

    public int getDaysId() {
        return daysId;
    }

    // setters


}
