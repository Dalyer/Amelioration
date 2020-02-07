package com.example.david.amelioration;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.Collections;
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
                Exercise x = new Exercise("New Exercise", "empty", 0);
                mExerciseList.addLast(x);
                mRecyclerView.getAdapter().notifyItemInserted(exerciseListSize);
                mRecyclerView.smoothScrollToPosition(exerciseListSize);
            }
            });

        // Put dummy data into exercise list
        // TODO ultimately this won't be a thing everything will be added from the button
        for (int i = 0; i <= 1; i++) {
            Exercise x = new Exercise("New Exercise", "empty", 0);
            mExerciseList.addLast(x);
        }

        // TODO at the end of the RecyclerView the last element should be a button for adding another element

        // Get a handle to the RecyclerView
        mRecyclerView = findViewById(R.id.recyclerview);
        // create an adapter and supply the data to be displayed
        mAdapter = new WorkoutListAdapter(this, mExerciseList);
        // connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // give the RecycleView a default layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // enable swiping to remove and dragging
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.DOWN | ItemTouchHelper.UP, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(mExerciseList, from, to);
                mAdapter.notifyItemMoved(from,to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                mExerciseList.remove(viewHolder.getAdapterPosition());
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        helper.attachToRecyclerView(mRecyclerView);
    }
}
