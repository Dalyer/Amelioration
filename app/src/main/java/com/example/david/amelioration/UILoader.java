package com.example.david.amelioration;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

public class UILoader extends AsyncTaskLoader<Schedule> {
    private String mAsyncDayName;
    private ScheduleRepository mAsyncRepository;
    private String mAsyncScheduleName;

    public UILoader(@NonNull Context context, ScheduleRepository repo, String dname, String sname) {
        super(context);
        mAsyncRepository = repo;
        mAsyncDayName = dname;
        mAsyncScheduleName = sname;

    }

    @Nullable
    @Override
    public Schedule loadInBackground() {
        return mAsyncRepository.getSchedule(mAsyncScheduleName);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}

