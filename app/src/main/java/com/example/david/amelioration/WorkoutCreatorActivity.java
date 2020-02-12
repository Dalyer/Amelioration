package com.example.david.amelioration;

import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.LinkedList;

public class WorkoutCreatorActivity extends AppCompatActivity {
    private LinkedList<Exercise> mExerciseList;
    private RecyclerView mRecyclerView;
    private WorkoutListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Parcelable mExerciseListState;
    private EditText mWorkoutName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_creator);
    // TODO add saveInstanceState information
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); // enables up button

        // Get view elements
        mWorkoutName = findViewById(R.id.workout_name);
        // Listen for entering a new name for scheduler
        if (mWorkoutName != null) {
            mWorkoutName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    Toast toast = Toast.makeText(WorkoutCreatorActivity.this, "hello", Toast.LENGTH_LONG);
                    toast.show();   // remove later
                    mWorkoutName.clearFocus();
                    return false;
                    // TODO save the schedule name stuff here
                }
            });
        }


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


        // Add initial New Exercise Button
        mExerciseList = new LinkedList<>();
        Exercise x = new Exercise("New Exercise", "empty", 0);
        mExerciseList.addLast(x);



        // Get a handle to the RecyclerView
        mRecyclerView = findViewById(R.id.recycler_view_workout_creator);
        // create an adapter and supply the data to be displayed
        mAdapter = new WorkoutListAdapter(this, mExerciseList);
        // connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // give the RecycleView a default layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mLayoutManager = mRecyclerView.getLayoutManager();

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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mExerciseListState = mLayoutManager.onSaveInstanceState();
        outState.putParcelable("test", mExerciseListState);

    }
    @Override
    protected void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);

        // Retrieve list state and list/item positions
        if (outState != null)
            mExerciseListState = outState.getParcelable("test");
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mExerciseListState != null) {
            mLayoutManager.onRestoreInstanceState(mExerciseListState);
        }
    }

}
