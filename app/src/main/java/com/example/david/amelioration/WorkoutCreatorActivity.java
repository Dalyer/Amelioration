package com.example.david.amelioration;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.LinkedList;

public class WorkoutCreatorActivity extends AppCompatActivity {
    private final LinkedList<Exercise> mExerciseList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private WorkoutListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_creator);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int exerciseListSize = mExerciseList.size();
                // add an item to this list
                Exercise x = new Exercise("test trash");
                mExerciseList.addLast(x);
                mRecyclerView.getAdapter().notifyItemInserted(exerciseListSize-1);
                mRecyclerView.smoothScrollToPosition(exerciseListSize-1);
            }
            });

        // Put dummy data into exercise list
        // TODO ultimately this won't be a thing everything will be added from the button
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
