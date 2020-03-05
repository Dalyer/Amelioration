package com.example.david.amelioration;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Day day);

    @Query("DELETE FROM day_table")
    void deleteAll();

    @Query("SELECT * from day_table ORDER BY dayName ASC")
    LiveData<List<Day>> getAllDays();

    @Query("SELECT * from day_table WHERE dayId IS (:dayId)")
    LiveData<List<Day>> getMatchingDays(int dayId);

    @Delete
    void deleteDay(Day day);

}
