package com.example.david.amelioration;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ScheduleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Schedule schedule);

    @Query("DELETE FROM schedule_table")
    void deleteAll();

    @Query("SELECT * from schedule_table ORDER BY scheduleName ASC")
    LiveData<List<Schedule>> getAllSchedules();
}