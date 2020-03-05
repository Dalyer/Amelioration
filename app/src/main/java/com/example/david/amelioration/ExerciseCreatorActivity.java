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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_creator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // get EditTexts
        EditText exerciseName = findViewById(R.id.name_edit_text);
        EditText exerciseDescription = findViewById(R.id.description_edit_text);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab_exercise);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO implement
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

}
