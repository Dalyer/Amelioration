package com.example.david.amelioration;

import android.os.AsyncTask;
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
    private Day mDayState;
    private String mDayName;
    private ScheduleRepository mScheduleRepository;
    private String mScheduleName; //TODO get this


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_creator);
    // TODO add saveInstanceState information
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set up database repository
        mScheduleRepository = new ScheduleRepository(getApplication());
        // get day name for current workout
        mDayName = getIntent().getStringExtra("name");
        mScheduleName = getIntent().getStringExtra("scheduleName");

        // Get day name
        mWorkoutName = findViewById(R.id.workout_name);
        // Listen for entering a new name for scheduler
        if (mWorkoutName != null) {
            mWorkoutName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    Toast toast = Toast.makeText(WorkoutCreatorActivity.this, "hello", Toast.LENGTH_LONG);
                    toast.show();   // remove later
                    mWorkoutName.clearFocus();
                    mAdapter.setWorkoutName(mWorkoutName.getText().toString());
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
        mDayState = new Day(mDayName, mExerciseList);


        // Get a handle to the RecyclerView
        mRecyclerView = findViewById(R.id.recycler_view_workout_creator);
        // create an adapter and supply the data to be displayed
        mAdapter = new WorkoutListAdapter(this, mExerciseList, mWorkoutName.getText().toString(), mScheduleName);
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
    protected void onResume() {
        super.onResume();
        //TODO update layout from database
    }

    public void saveWorkout(View view) {
        new getScheduleAsyncTask(mScheduleRepository, mScheduleName, mDayName, mExerciseList, mWorkoutName.getText().toString()).execute();
        Toast toast = Toast.makeText(WorkoutCreatorActivity.this, "Workout Saved", Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }

    private static class getScheduleAsyncTask extends AsyncTask<String, Void, Schedule> {

        private ScheduleRepository mRepository;
        private String mScheduleName;
        private Schedule mSchedule;
        private String mDayName;
        private LinkedList<Exercise> mUpdatedExercises;
        private String mWorkoutName;

        getScheduleAsyncTask(ScheduleRepository repo, String scheduleName, String dayName, LinkedList<Exercise> exercises, String name) {
            mRepository = repo;
            mScheduleName = scheduleName;
            mDayName = dayName;
            mUpdatedExercises = exercises;
            mWorkoutName = name;
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
            for(int i=0; i < days.size(); i++) {
                Day currDay = days.get(i);
                if(currDay.getName().equals(mDayName)) {
                    //Update exercise linked list and name
                    days.remove(i);
                    currDay.updateExercises(mUpdatedExercises);
                    currDay.updateName(mWorkoutName);
                    //update schedule
                    days.add(i, currDay);
                    mSchedule.updateWorkouts(days);
                    // update database
                    mRepository.insert(mSchedule);
                }
            }
            // TODO move workout saved toast here
        }
    }
}
