package com.example.david.amelioration;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "schedule_table")
public class Schedule {

    @PrimaryKey
    private int scheduleId;     // this needs to be unique
    @NonNull
    private String scheduleName;
    private int daysId;         // this needs to be unique


    public Schedule(int scheduleId, @NonNull String scheduleName, int daysId) {
        this.scheduleName = scheduleName;
        this.daysId = daysId;
        this.scheduleId = scheduleId;
    }

    // getters
    public int getScheduleId() {
        return scheduleId;
    }

    @NonNull
    public String getScheduleName() {
        return scheduleName;
    }

    public int getDaysId() {
        return daysId;
    }

    // setters


}
