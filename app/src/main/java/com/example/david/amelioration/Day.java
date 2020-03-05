package com.example.david.amelioration;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName= "day_table")
public class Day {

    @PrimaryKey
    private int dayId;       // needs to be unique
    private String dayName;
    private int order;
    private int exercisesId;


    Day(int dayId, String dayName, int exercisesId, int order) {
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

    // setters
    //TODO add a setter for order

}
