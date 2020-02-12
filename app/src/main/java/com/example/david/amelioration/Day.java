package com.example.david.amelioration;

import java.util.LinkedList;


@Entity(tableName = )
public class Day {
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


}
