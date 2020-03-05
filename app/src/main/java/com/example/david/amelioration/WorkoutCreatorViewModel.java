package com.example.david.amelioration;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class WorkoutCreatorViewModel extends AndroidViewModel {
    private ScheduleRepository mRepo;
    // Schedule
    private LiveData<List<Schedule>> mAllSchedules;
    private LiveData<Schedule> mCurrentSchedule;
    // Day
    private LiveData<List<Day>> mAllMatchingDays;
    // Exercise
    private LiveData<List<Exercise>> mAllMatchingExercises;


    public WorkoutCreatorViewModel(@NonNull Application application, int scheduleId, int dayId, int exerciseId) {
        super(application);
        mRepo = new ScheduleRepository(application, scheduleId, dayId, exerciseId);
        mAllSchedules = mRepo.getAllSchedules();
        mAllMatchingDays = mRepo.getmAllMatchingDays();
        mAllMatchingExercises = mRepo.getmAllMatchingExercises();
    }

    LiveData<List<Schedule>> getAllSchedules () { //TODO what
        return mAllSchedules;
    }

    LiveData<Schedule> getCurrentSchedule() {
        return mCurrentSchedule;
    }

    LiveData<List<Day>> getmAllMatchingDays() {
        return mAllMatchingDays;
    }

    LiveData<List<Exercise>> getmAllMatchingExercises() {
        return mAllMatchingExercises;
    }

    public void insert(Schedule schedule) {
        mRepo.insert(schedule);
    }

    public void insert(Day day) {
        mRepo.insert(day);
    }

    public void insert(Exercise exercise) {
        mRepo.insert(exercise);
    }
}
