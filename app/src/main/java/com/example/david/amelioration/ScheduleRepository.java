package com.example.david.amelioration;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ScheduleRepository {
    // Member variables
    private ScheduleDao mScheduleDao;
    private LiveData<List<Schedule>> mAllSchedules;

    public ScheduleRepository(Application application) {
        ScheduleRoomDatabase db = ScheduleRoomDatabase.getDatabase(application);
        mScheduleDao = db.scheduleDao();
        mAllSchedules = mScheduleDao.getAllSchedules();
    }

    LiveData<List<Schedule>> getAllSchedules() {
        return mAllSchedules;
    }

    public void insert(Schedule schedule) {
        new insertAsyncTask(mScheduleDao).execute(schedule);
    }

    public void deleteAll() {
        new deleteAllAsyncTask(mScheduleDao).execute();
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private ScheduleDao mAsyncTaskDao;

        deleteAllAsyncTask(ScheduleDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class insertAsyncTask extends AsyncTask<Schedule, Void, Void> {

        private ScheduleDao mAsyncTaskDao;

        insertAsyncTask(ScheduleDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Schedule... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
