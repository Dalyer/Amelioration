package com.example.david.amelioration;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class DayViewModel extends AndroidViewModel {
    private ScheduleRepository mRepo;
    private LiveData<List<Schedule>> mAllSchedules;


    public DayViewModel(@NonNull Application application) {
        super(application);
        mRepo = new ScheduleRepository(application);
        mAllSchedules = mRepo.getAllSchedules();
    }

    LiveData<List<Schedule>> getAllSchedules () {
        return mAllSchedules;
    }

    public void insert(Schedule schedule) {
        mRepo.insert(schedule);
    }
}
