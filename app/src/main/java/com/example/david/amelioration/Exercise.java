package com.example.david.amelioration;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "exercise_table")
class Exercise {

    @PrimaryKey
    private String name;
    private String description;
    private int restTimeMs;

    Exercise(String name, String description, int restTimeMs) {
        this.name = name;
        this.description = description;
        this.restTimeMs = restTimeMs;
    }
    // TODO finish implementing this class, adding stats, etc

    // Getter functions
    public String getName() {
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getRestTime() {
        return restTimeMs;
    }

    // setters
    public void updateName(String name) {
        this.name = name;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void updateRestTimeMs(int restTime) {
        this.restTimeMs = restTime;
    }
}
