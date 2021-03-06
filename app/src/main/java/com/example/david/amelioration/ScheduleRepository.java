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
        mAllMatchingDays = mDayDao.getMatchingDays(scheduleId);
        mExerciseDao = db.exerciseDao();
        mAllMatchingExercises = mExerciseDao.getMatchingExercises(dayId);
    }

    // Schedule Based methods

    LiveData<List<Schedule>> getAllSchedules() {
        return mAllSchedules;
    }

    public void insert(Schedule schedule) {
        new insertScheduleAsyncTask(mScheduleDao).execute(schedule);
    }

    public void deleteAll() {
        new deleteAllAsyncTask(mScheduleDao, mDayDao, mExerciseDao).execute();
    }

    // TODO add a wrapper for getting a specific schedule

    // Day based methods
    LiveData<List<Day>> getmAllMatchingDays() {
        return mAllMatchingDays;
    }

    public void insert(Day day) {
        new insertDayAsyncTask(mDayDao).execute(day);
    }

    public void deleteDay(Day day) {
        new deleteDayAsyncTask(mDayDao).execute(day);
    }

    // Exercise based methods
    LiveData<List<Exercise>> getmAllMatchingExercises() {
        return mAllMatchingExercises;
    }

    public void insert(Exercise exercise) {
        new insertExerciseAsyncTask(mExerciseDao).execute(exercise);
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private ScheduleDao mAsyncTaskScheduleDao;
        private DayDao mAsyncTaskDayDao;
        private ExerciseDao mAsyncTaskExerciseDao;

        deleteAllAsyncTask(ScheduleDao sdao, DayDao ddao,ExerciseDao edao) {
            mAsyncTaskScheduleDao = sdao;
            mAsyncTaskDayDao = ddao;
            mAsyncTaskExerciseDao = edao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskScheduleDao.deleteAll();
            mAsyncTaskDayDao.deleteAll();
            mAsyncTaskExerciseDao.deleteAll();
            return null;
        }
    }

    private static class insertScheduleAsyncTask extends AsyncTask<Schedule, Void, Void> {

        private ScheduleDao mAsyncTaskDao;

        insertScheduleAsyncTask(ScheduleDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Schedule... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class insertDayAsyncTask extends AsyncTask<Day, Void, Void> {

        private DayDao mAsyncTaskDao;

        insertDayAsyncTask(DayDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Day... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class insertExerciseAsyncTask extends AsyncTask<Exercise, Void, Void> {

        private ExerciseDao mAsyncTaskDao;

        insertExerciseAsyncTask(ExerciseDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Exercise... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteDayAsyncTask extends AsyncTask<Day, Void, Void> {

        private DayDao mAsyncTaskDao;

        deleteDayAsyncTask(DayDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Day... params) {
            mAsyncTaskDao.deleteDay(params[0]);
            return null;
        }
    }

}
