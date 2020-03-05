package com.example.david.amelioration;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
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
import java.util.List;


public class DayCreatorActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private DayListAdapter mAdapter;
    private EditText mScheduleEditText;
    private WorkoutCreatorViewModel mWorkoutCreatorViewModel;
    private int mScheduleId;
    private int mDayId;
    private int mExerciseId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_creator);
        Toolbar toolbar = findViewById(R.id.toolbar_day_creator);
        setSupportActionBar(toolbar);

        // Get Schedule naming field
        mScheduleEditText = findViewById(R.id.schedule_name);
        // Listen for entering a new name for schedule
        if (mScheduleEditText != null) {
            mScheduleEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    Toast toast = Toast.makeText(DayCreatorActivity.this, "hello", Toast.LENGTH_LONG);
                    toast.show();   // remove later
                    mScheduleEditText.clearFocus();
                    //mAdapter.setScheduleName(mScheduleEditText.getText().toString());     // TODO Remove and just store in a member variable???
                    return false;
                }
            });
        }


        FloatingActionButton fab = findViewById(R.id.fab_day_creator);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create new day
                // increment exerciseId for the new day
                mExerciseId++;
                Day newDay = new Day(mDayId,"New Day", mExerciseId, 0);
                mWorkoutCreatorViewModel.insert(newDay);
            }
        });

        // Set view model
        mWorkoutCreatorViewModel = ViewModelProviders.of(this).get(WorkoutCreatorViewModel.class);

        // initialize a schedule object and a day table for the view model
        initializeData();

        // Get a handle to the RecyclerView
        mRecyclerView = findViewById(R.id.recycler_view_day_creator);
        // create an adapter and supply the data to be displayed
        mAdapter = new DayListAdapter(this);
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
                //Collections.swap(mWorkoutCreatorViewModel.getmAllMatchingDays(), from, to); TODO might need to fix this or just use the orders later
                mAdapter.notifyItemMoved(from,to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                int position = viewHolder.getAdapterPosition();
                Day deletedDay = mAdapter.getDayAtPosition(position);
                mWorkoutCreatorViewModel.deleteDay(deletedDay);
            }
        });
        helper.attachToRecyclerView(mRecyclerView);

        // Observer for list of day changes
        mWorkoutCreatorViewModel.getmAllMatchingDays().observe(this, new Observer<List<Day>>() {
            @Override
            public void onChanged(@Nullable List<Day> days) {
                // Find workout
                mAdapter.setDays(days);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        // TODO update layout from database
    }

    @Override
    protected void onPause() {
        super.onPause();
       // mScheduleState.updateWorkouts(mDayList); todo un-break
    }


    public void initializeData() {
        // TODO create a unique scheduleID and mDayId
        mScheduleId = 1;
        mDayId = 1;
        mExerciseId = 2;
        // Create the base schedule
        Schedule scheduleBase = new Schedule(mScheduleId, mScheduleEditText.getText().toString(), mDayId);
        mWorkoutCreatorViewModel.insert(scheduleBase);
        // Create the base day object
        Day dayBase = new Day(mDayId,"New Day", mExerciseId, 0);
        mWorkoutCreatorViewModel.insert(dayBase);
        // Create the base exercise object
        Exercise exerciseBase = new Exercise(mExerciseId, "New Exercise", "", 0, 0);
        mWorkoutCreatorViewModel.insert(exerciseBase);
    }
}
