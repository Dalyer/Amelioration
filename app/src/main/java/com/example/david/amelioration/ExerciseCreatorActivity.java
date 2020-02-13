package com.example.david.amelioration;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedList;

public class ExerciseCreatorActivity extends AppCompatActivity {

    // Member variables
    private String mExerciseName;
    private String mExerciseDescription;
    private int mExerciseRestTime;
    private String mWorkoutName;
    private String mScheduleName;
    private ScheduleRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_creator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // get EditTexts
        EditText exerciseName = findViewById(R.id.name_edit_text);
        EditText exerciseDescription = findViewById(R.id.description_edit_text);
        // Get Spinners

        //set repository
        mRepository = new ScheduleRepository(getApplication());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab_exercise);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveExercise();
            }
        });

        // Set values
        mExerciseName = getIntent().getStringExtra("exercise_name");
        mExerciseDescription = getIntent().getStringExtra("description");
        mExerciseRestTime = getIntent().getIntExtra("rest_time", 0);
        mWorkoutName = getIntent().getStringExtra("workout_name");
        mScheduleName = getIntent().getStringExtra("schedule_name");
        // Set data if data already there
        if (mExerciseDescription.equals("empty")) {

        } else {
            exerciseDescription.setText(mExerciseDescription);
        }
        if (mExerciseRestTime != 0) {
            // set spinner stuff here
        }
        exerciseName.setText(mExerciseName);

    }

    public void saveExercise() {
        // get the schedule from the database then find the correct day then update the specific exercise from the list
        new updateExerciseAsyncTask(mRepository, mScheduleName, mExerciseName, mWorkoutName, mExerciseDescription, mExerciseRestTime);
        Toast toast = Toast.makeText(this, "Exercise Saved", Toast.LENGTH_SHORT);
        toast.show();
    }

    private static class updateExerciseAsyncTask extends AsyncTask<String, Void, Schedule> {

        private ScheduleRepository mRepository;
        private String mScheduleName;
        private Schedule mSchedule;
        private String mExerciseName;
        private String mDayName;

        private String mExerciseDescription;
        private int mExerciseRestTime;

        updateExerciseAsyncTask(ScheduleRepository repo, String scheduleName, String exerciseName, String dayName, String description, int restTime) {
            mRepository = repo;
            mScheduleName = scheduleName;
            mExerciseName = exerciseName;
            mDayName = dayName;
            mExerciseDescription = description;
            mExerciseRestTime = restTime;
        }

        @Override
        protected Schedule doInBackground(String... strings) {
            mSchedule = mRepository.getSchedule(mScheduleName);
            return mSchedule;
        }

        @Override
        protected void onPostExecute(Schedule schedule) {
            super.onPostExecute(schedule);
            // find Day/workout to replace
            LinkedList<Day> days = mSchedule.getWorkouts();
            for (int i = 0; i < days.size(); i++) {
                Day currDay = days.get(i);
                if (currDay.getName().equals(mDayName)) {
                    //Update exercise linked list
                    LinkedList<Exercise> currExercises = currDay.getExercises();
                    for(int j = 0; j < currExercises.size(); j++) {
                        Exercise currExercise = currExercises.get(j);
                        if(currExercise.getName().equals(mExerciseName)) {
                            currExercises.remove(j);
                            currExercise.updateDescription(mExerciseDescription);
                            currExercise.updateName(mExerciseName);
                            currExercise.updateRestTimeMs(mExerciseRestTime);
                            currExercises.add(j, currExercise);
                            currDay.updateExercises(currExercises);
                            mSchedule.updateWorkouts(days);
                            mRepository.insert(mSchedule);
                        }
                    }
                }
            }
            // TODO move workout saved toast here
        }
    }

}
