package com.example.david.amelioration;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "exercise_table")
class Exercise {

    @PrimaryKey
    private int exerciseId;         // needs to be unique
    private String exerciseName;
    private String description;
    private int restTimeMs;
    private int order;

    Exercise(int exerciseId, String exerciseName, String description, int restTimeMs, int order) {
        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
        this.description = description;
        this.restTimeMs = restTimeMs;
        this.order = order;
    }
    // TODO finish implementing this class, adding stats, etc

    // Getter functions
    public int getExerciseId() {
        return exerciseId;
    }
    public String getExerciseName() {
        return exerciseName;
    }

    public String getDescription(){
        return description;
    }

    public int getRestTimeMs() {
        return restTimeMs;
    }

    public int getOrder() {
        return order;
    }

    // setters
    public void updateName(String name) {
        this.exerciseName = name;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void updateRestTimeMs(int restTime) {
        this.restTimeMs = restTime;
    }
}
