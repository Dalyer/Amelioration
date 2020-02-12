package com.example.david.amelioration;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class ScheduleViewModel extends AndroidViewModel {

    private ScheduleRepository mRepository;

    private LiveData<List<Schedule>> mAllSchedules;

    public ScheduleViewModel (Application application) {
        super(application);
        mRepository = new ScheduleRepository(application);
        mAllSchedules = mRepository.getAllSchedules();
    }

    LiveData<List<Schedule>> getAllSchedules() { return mAllSchedules; }

    public void insert(Schedule schedule) { mRepository.insert(schedule); }
}
