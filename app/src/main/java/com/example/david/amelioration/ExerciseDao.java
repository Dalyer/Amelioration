package com.example.david.amelioration;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Exercise exercise);

    @Query("DELETE FROM exercise_table")
    void deleteAll();

    @Query("SELECT * from exercise_table ORDER BY exerciseName ASC")
    LiveData<List<Exercise>> getAllExercises();

    @Query("SELECT * from exercise_table WHERE exerciseId IS (:exerciseId)")
    LiveData<List<Exercise>> getMatchingExercises(int exerciseId);

}

