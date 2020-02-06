package com.example.david.amelioration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedList;

public class WorkoutCreatorActivity extends AppCompatActivity {
    private final LinkedList<Exercise> mExerciseList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private WorkoutListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_creator);

        // Put dummy data into exercise list
        for (int i = 0; i < 20; i++) {
            String tmp = "exercise" + i;
            Exercise x = new Exercise(tmp);
            mExerciseList.addLast(x);
        }

        // TODO at the end of the RecyclerView the last element should be a button for adding another element

        // Get a handle to the RecyclerView
        mRecyclerView = findViewById(R.id.recyclerview);
        // create an adapter and supply the data to be displayed
        mAdapter = new WorkoutListAdapter(this, mExerciseList);
        // connect the adapte rwith the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // give the RecycleView a default layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
