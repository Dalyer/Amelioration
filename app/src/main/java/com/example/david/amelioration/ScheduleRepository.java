package com.example.david.amelioration;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ScheduleRepository {
    // Member variables
    // Schedule
    private ScheduleDao mScheduleDao;
    private LiveData<List<Schedule>> mAllSchedules;
    // Day
    private DayDao mDayDao;
    private LiveData<List<Day>> mAllMatchingDays;
    // Exercise
    private ExerciseDao mExerciseDao;
    private LiveData<List<Exercise>> mAllMatchingExercises;

    public ScheduleRepository(Application application, int scheduleId, int dayId, int exerciseId) {
        ScheduleRoomDatabase db = ScheduleRoomDatabase.getDatabase(application);
        mScheduleDao = db.scheduleDao();
        mAllSchedules = mScheduleDao.getAllSchedules();
        mDayDao = db.dayDao();
        mAllMatchingDays = mDayDao.getMatchingDays(dayId);
        mExerciseDao = db.exerciseDao();
        mAllMatchingExercises = mExerciseDao.getMatchingExercises(exerciseId);
    }

    // Schedule Based methods

    LiveData<List<Schedule>> getAllSchedules() {
        return mAllSchedules;
    }

    public void insert(Schedule schedule) {
        new insertAsyncTask(mScheduleDao).execute(schedule);
    }

    public void deleteAll() {
        new deleteAllAsyncTask(mScheduleDao).execute();
    }

    // TODO add a wrapper for getting a specific schedule

    // Day based methods
    LiveData<List<Day>> getmAllMatchingDays() {
        return mAllMatchingDays;
    }
    // Exercise based methods
    LiveData<List<Exercise>> getmAllMatchingExercises() {
        return mAllMatchingExercises;
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
