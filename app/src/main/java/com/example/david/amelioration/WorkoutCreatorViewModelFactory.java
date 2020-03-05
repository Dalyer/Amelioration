package com.example.david.amelioration;


import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class WorkoutCreatorViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private int mScheduleId;
    private int mDayId;
    private int mExerciseId;

    public WorkoutCreatorViewModelFactory(Application application, int scheduleId, int dayId, int exerciseId) {
        this.mApplication = application;
        this.mScheduleId = scheduleId;
        this.mDayId = dayId;
        this.mExerciseId = exerciseId;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new WorkoutCreatorViewModel(mApplication, mScheduleId, mDayId, mExerciseId);
    }
}
