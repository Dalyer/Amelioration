package com.example.david.amelioration;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName= "day_table")
public class Day {

    @PrimaryKey
    private int dayId;       // needs to be unique
    private int scheduleId;  // the id of the schedule that created it
    private String dayName;
    private int order;
    private int exercisesId;


    Day(int scheduleId, int dayId, String dayName, int exercisesId, int order) {
        this.scheduleId = scheduleId;
        this.dayId = dayId;
        this.dayName = dayName;
        this.exercisesId = exercisesId;
        this.order = order;

    }

    // getters
    public String getDayName(){
        return dayName;
    }

    public int getExercisesId() {
        return exercisesId;
    }

    public int getDayId() {
        return dayId;
    }

    public int getOrder() {
        return order;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    // setters
    //TODO add a setter for order

}
