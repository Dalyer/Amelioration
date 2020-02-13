package com.example.david.amelioration;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.LinkedList;

@Database(entities = {Schedule.class}, version=1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class ScheduleRoomDatabase extends RoomDatabase {
    private static ScheduleRoomDatabase INSTANCE;
    public abstract ScheduleDao scheduleDao();

    static ScheduleRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized ( (ScheduleRoomDatabase.class)) {
                if (INSTANCE == null) {
                    // create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext()
                            , ScheduleRoomDatabase.class, "schedule_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };
    // Populate Database in the background TODO not needed?
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ScheduleDao mDao;
        String[] words = {"dolphin", "crocodile", "cobra"};

        PopulateDbAsync(ScheduleRoomDatabase db) {
            mDao = db.scheduleDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
//            mDao.deleteAll();
//// TODO replace all this
//            for (int i = 0; i <= words.length - 1; i++) {
//                LinkedList<Day> temp = new LinkedList<>();
//                LinkedList<Exercise> x = new LinkedList<>();
//                temp.add(0, new Day("test", x));
//                Schedule schedule = new Schedule(words[i], temp);
//                mDao.insert(schedule);
//            }
            return null;
        }
    }
}

