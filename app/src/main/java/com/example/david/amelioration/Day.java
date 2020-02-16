package com.example.david.amelioration;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.LinkedList;


@Entity(tableName= "day_table")
public class Day {

    @PrimaryKey
    private String name;
    private LinkedList<Exercise> exercises;


    Day(String name, LinkedList<Exercise> exercises) {
        this.name = name;
        this.exercises = exercises;
    }

    // getters
    public String getName(){
        return name;
    }


    public LinkedList<Exercise> getExercises() {
        return exercises;
    }

    // setters

    public void updateExercises(LinkedList<Exercise> updatedExercises) {
        exercises = updatedExercises;
    }


    public void updateName(String workoutName) {
        name = workoutName;
    }
}
